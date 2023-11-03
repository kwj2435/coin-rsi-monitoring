package com.uijin.community.service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CoinService {
  private final int RSI_DAY = 8;
  private final int RSI_MAX_VALUE = 92;
  private final int RSI_MIN_VALUE = 8;
  private final String CHAT_ID = "6645481472";
  private final List<String> coinSymbolList =
      List.of(
          "BLZ_USDT", "LINK_USDT", "LOOKS_USDT", "GAS_USDT",
          "CYBER_USDT", "UNFI_USDT", "FTT_USDT", "STORJ_USDT",
          "MASK_USDT", "1000PEPE2_USDT","MTL_USDT", "1000BONK_USDT", "TIP_USDT",
          "TRB_USDT", "BTC_USDT", "SUSHI_USDT", "TURBO_USDT", "XMR_USDT", "SLP_USDT",
          "TOMO_USDT", "BLUR_USDT", "HIFI_USDT", "RUNE_USDT", "WLD_USDT", "POLYX_USDT",
          "POWR_USDT", "INJ_USDT", "TARA_USDT", "CAKE_USDT", "TWT_USDT", "HOT_USDT",
          "CHZ_USDT", "FILECOIN_USDT");

  @Scheduled(cron = "0 */2 * * * *")
  public void scheduler() throws InterruptedException {
    RestTemplate restTemplate = new RestTemplate();
    System.out.println(LocalDateTime.now());
    for(int i = 0 ; i<coinSymbolList.size() ; i++) {
      System.out.println(coinSymbolList.get(i));
      if(i % 20 == 0) { Thread.sleep(1000); }
      if(checkRsi(coinSymbolList.get(i))) {
        URI uri = UriComponentsBuilder
            .fromUriString("https://api.telegram.org")
            .path("bot6718525078:AAFBFUxW32Sw7luc-U0fOqh7dc4VKb4pDQk/sendmessage")
            .queryParam("text", i+" - RSI 진입 시점")
            .queryParam("chat_id", CHAT_ID)
            .encode()
            .build()
            .toUri();
        restTemplate.getForObject(uri, String.class);
      }
    }
  }

  public boolean checkRsi(String symbol) {
    double rsi = getRsiByMinutes(symbol);
    return rsi > RSI_MAX_VALUE || rsi < RSI_MIN_VALUE;
  }

  private double getRsiByMinutes(String symbol) {
    RestTemplate restTemplate = new RestTemplate();

    long end = ZonedDateTime.now().toEpochSecond();
    long start = ZonedDateTime.now().minusHours(8).toEpochSecond();
    URI uri = UriComponentsBuilder
        .fromUriString("https://contract.mexc.com")
        .path("api/v1/contract/kline/" + symbol).queryParam("interval", "Min5").queryParam("start", start).queryParam("end", end)
        .encode()
        .build()
        .toUri();
    String forObject = restTemplate.getForObject(uri, String.class);
    JSONObject jsonObject = new JSONObject(forObject);
    JSONObject jsonObject1 = new JSONObject(jsonObject.get("data").toString());
    List<Object> close = jsonObject1.getJSONArray("close").toList();
    List<Double> candleResList = new ArrayList<>();
    for(Object i : close) {
      if(i.getClass().getName().equals("java.math.BigDecimal")) {
        candleResList.add(((BigDecimal)i).doubleValue());
      } else {
        candleResList.add((double)(int) i);
      }
    }

    if (CollectionUtils.isEmpty(candleResList)) {
      throw new IllegalArgumentException("데이터가 존재하지 않습니다 : " + symbol);
    }

    // 지수 이동 평균은 과거 데이터부터 구해주어야 합니다.
//    candleResList = candleResList.stream()
//        .sorted(Comparator.comparing(CandleRes::getTimestamp))  // 오름차순 (과거 순)
//        .collect(Collectors.toList());  // Sort

    double zero = 0;
    List<Double> upList = new ArrayList<>();  // 상승 리스트
    List<Double> downList = new ArrayList<>();  // 하락 리스트
    for (int i = 0; i < candleResList.size() - 1; i++) {
      // 최근 종가 - 전일 종가 = gap 값이 양수일 경우 상승했다는 뜻 / 음수일 경우 하락이라는 뜻
      double gapByTradePrice = candleResList.get(i + 1) - candleResList.get(i);
      if (gapByTradePrice > 0) {  // 종가가 전일 종가보다 상승일 경우
        upList.add(gapByTradePrice);
        downList.add(zero);
      } else if (gapByTradePrice < 0) {  // 종가가 전일 종가보다 하락일 경우
        downList.add(gapByTradePrice * -1);  // 음수를 양수로 변환해준다.
        upList.add(zero);
      } else {  // 상승, 하락이 없을 경우 종가 - 전일 종가 = gap은 0이므로 0값을 넣어줍니다.
        upList.add(zero);
        downList.add(zero);
      }
    }

    double a = (double) 1 / (1 + (RSI_DAY - 1));  // 지수 이동 평균의 정식 공식은 a = 2 / 1 + day 이지만 업비트에서 사용하는 수식은 a = 1 / (1 + (day - 1))

    // AU값 구하기
    double upEma = 0;  // 상승 값의 지수이동평균
    if (!CollectionUtils.isEmpty(upList)) {
      upEma = upList.get(0).doubleValue();
      if (upList.size() > 1) {
        for (int i = 1 ; i < upList.size(); i++) {
          upEma = (upList.get(i).doubleValue() * a) + (upEma * (1 - a));
        }
      }
    }

    // AD값 구하기
    double downEma = 0;  // 하락 값의 지수이동평균
    if (!CollectionUtils.isEmpty(downList)) {
      downEma = downList.get(0).doubleValue();
      if (downList.size() > 1) {
        for (int i = 1; i < downList.size(); i++) {
          downEma = (downList.get(i).doubleValue() * a) + (downEma * (1 - a));
        }
      }
    }

    // rsi 계산
    double au = upEma;
    double ad = downEma;
    double rs = au / ad;

    return 100 - (100 / (1 + rs));
  }
}
