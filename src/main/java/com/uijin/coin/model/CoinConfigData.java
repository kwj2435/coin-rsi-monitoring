package com.uijin.coin.model;

import com.uijin.coin.model.ConfigModel.ConfigModifyRequest;
import org.springframework.stereotype.Component;

@Component
public class CoinConfigData {
    // RSI 계산 주기
    public static int RSI_DAY = 7;
    // RSI 과매수 알림 수치
    public static int RSI_MAX_VALUE = 89;
    // RSI 과매도 알림 수치
    public static int RSI_MIN_VALUE = 9;

    public static int MAXC_API_CALL_SLEEP_VALUE = 1500;

    public static int ALERT_IGNORE_CYCLE = 2;

    public static void modify(ConfigModifyRequest configModifyRequest) {
        int value = Integer.parseInt(configModifyRequest.getValue());

        switch (configModifyRequest.getConfigItem()) {
            case RSI_DAY : {
                RSI_DAY = value;
                break;
            }
            case RSI_MAX_VALUE: {
                if(value < 85) { throw new IllegalArgumentException("RSI MAX VALUE는 85보다 작을 수 없습니다."); }
                RSI_MAX_VALUE = value;
                break;
            }
            case RSI_MIN_VALUE: {
                if(value > 15) { throw new IllegalArgumentException("RSI MIN VALUE는 15보다 클 수 없습니다."); }
                RSI_MIN_VALUE = value;
                break;
            }
            case ALERT_IGNORE_CYCLE: {
                ALERT_IGNORE_CYCLE = value;
                break;
            }
            case MAXC_API_CALL_SLEEP_VALUE: {
                MAXC_API_CALL_SLEEP_VALUE = value;
                break;
            }
        }
    }
}
