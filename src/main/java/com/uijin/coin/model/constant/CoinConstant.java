package com.uijin.coin.model.constant;

import java.util.List;

public class CoinConstant {
    // RSI 계산 주기
    public static final int RSI_DAY = 8;
    // RSI 과매수 알림 수치
    public static final int RSI_MAX_VALUE = 89;
    // RSI 과매도 알림 수치
    public static final int RSI_MIN_VALUE = 9;

    // 텔레그램 채팅방 ID
    public static final String CHAT_ID = "6645481472";

    // 모니터링 대상 코인 리스트
    public static final List<String> COIN_LIST =
            List.of(
            "BLZ_USDT", "LINK_USDT", "LOOKS_USDT", "GAS_USDT",
            "CYBER_USDT", "UNFI_USDT", "FTT_USDT", "STORJ_USDT",
            "MASK_USDT", "1000PEPE2_USDT","MTL_USDT", "1000BONK_USDT", "TIP_USDT",
            "TRB_USDT", "BTC_USDT", "SUSHI_USDT", "TURBO_USDT", "XMR_USDT", "SLP_USDT",
            "TOMO_USDT", "BLUR_USDT", "HIFI_USDT", "RUNE_USDT", "WLD_USDT", "POLYX_USDT",
            "POWR_USDT", "INJ_USDT", "TARA_USDT", "CAKE_USDT", "TWT_USDT", "HOT_USDT",
            "CHZ_USDT", "FILECOIN_USDT", "LQTY_USDT", "FLOKI_USDT", "NEAR_USDT",
            "SFP_USDT", "ENJ_USDT", "CORE_USDT", "KAS_USDT", "WEMIX_USDT", "ARK_USDT",
            "NEO_USDT", "KSM_USDT", "IMX_USDT", "XVS_USDT", "UNI_USDT"
            );
}
