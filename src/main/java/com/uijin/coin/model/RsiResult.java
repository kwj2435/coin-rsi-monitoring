package com.uijin.coin.model;

import static com.uijin.coin.model.CoinConfigData.RSI_MAX_VALUE;
import static com.uijin.coin.model.CoinConfigData.RSI_MIN_VALUE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RsiResult {
  private boolean isTargetAlert;
  private double rsi;

  public static RsiResult create(double rsi) {
    boolean isTargetAlert = rsi > RSI_MAX_VALUE || rsi < RSI_MIN_VALUE;
    return new RsiResult(isTargetAlert, rsi);
  }
}
