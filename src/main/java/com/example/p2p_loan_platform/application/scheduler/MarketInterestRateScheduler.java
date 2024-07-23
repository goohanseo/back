package com.example.p2p_loan_platform.application.scheduler;

import com.example.p2p_loan_platform.application.service.MarketInterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketInterestRateScheduler {

    @Autowired
    private MarketInterestRateService marketInterestRateService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void fetchMarketInterestRateMonthly() {
        marketInterestRateService.fetchAndSaveMarketInterestRate();
    }
}