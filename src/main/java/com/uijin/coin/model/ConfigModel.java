package com.uijin.coin.model;

import static com.uijin.coin.model.CoinConfigData.ALERT_IGNORE_CYCLE;
import static com.uijin.coin.model.CoinConfigData.MAXC_API_CALL_SLEEP_VALUE;
import static com.uijin.coin.model.CoinConfigData.RSI_DAY;
import static com.uijin.coin.model.CoinConfigData.RSI_MAX_VALUE;
import static com.uijin.coin.model.CoinConfigData.RSI_MIN_VALUE;
import static com.uijin.coin.model.constant.CoinConstant.CRON_CYCLE;

import com.uijin.coin.model.enums.ConfigItem;
import lombok.Getter;

public class ConfigModel {

    @Getter
    public static class ConfigResponse {
        private int rsiDay = RSI_DAY;
        private int rsiMaxValue = RSI_MAX_VALUE;
        private int rsiMinValue = RSI_MIN_VALUE;
        private String cronCycle = CRON_CYCLE;
        private int mexcApiCallSleepValue = MAXC_API_CALL_SLEEP_VALUE;
        private int alertIgnoreCycle = ALERT_IGNORE_CYCLE;

        public static ConfigResponse create() {
            return new ConfigResponse();
        }
    }

    @Getter
    public static class ConfigModifyRequest {
        private ConfigItem configItem;
        private String value;
    }
}
