package com.uijin.coin.service;

import com.uijin.coin.model.CoinConfigData;
import com.uijin.coin.model.ConfigModel.ConfigModifyRequest;
import com.uijin.coin.model.ConfigModel.ConfigResponse;
import org.springframework.stereotype.Service;

@Service
public class CoinConfigService {

    public ConfigResponse modifyConfig(ConfigModifyRequest configModifyRequest) {
        CoinConfigData.modify(configModifyRequest);

        return new ConfigResponse();
    }
}
