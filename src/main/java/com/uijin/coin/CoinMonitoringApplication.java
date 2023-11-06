package com.uijin.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoinMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinMonitoringApplication.class, args);
	}

}
