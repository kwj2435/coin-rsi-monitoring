package com.uijin.coin.controller;

import com.uijin.coin.model.ConfigModel;
import com.uijin.coin.model.ConfigModel.ConfigModifyRequest;
import com.uijin.coin.model.ConfigModel.ConfigResponse;
import com.uijin.coin.service.CoinConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coin/config")
@RequiredArgsConstructor
public class CoinConfigController {

    private final CoinConfigService coinConfigService;

    @GetMapping
    public ConfigModel.ConfigResponse get() {
        return ConfigResponse.create();
    }

    @PutMapping
    public ConfigResponse put(@RequestBody ConfigModifyRequest configModifyRequest) {
        return coinConfigService.modifyConfig(configModifyRequest);
    }
}
