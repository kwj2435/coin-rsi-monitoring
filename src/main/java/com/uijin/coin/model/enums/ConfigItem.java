package com.uijin.coin.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigItem {
    RSI_DAY,
    RSI_MIN_VALUE,
    RSI_MAX_VALUE,
    MAXC_API_CALL_SLEEP_VALUE,
    ALERT_IGNORE_CYCLE
}
