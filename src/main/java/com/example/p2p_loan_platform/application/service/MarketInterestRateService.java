package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.MarketInterestRate;
import com.example.p2p_loan_platform.application.domain.repository.MarketInterestRateRepository;
import com.example.p2p_loan_platform.application.dto.MarketInterestRateDto;
import com.example.p2p_loan_platform.application.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MarketInterestRateService {

    @Autowired
    private MarketInterestRateRepository marketInterestRateRepository;

    public MarketInterestRateDto getLatestMarketInterestRate() {
        MarketInterestRate marketInterestRate = marketInterestRateRepository.findTopByOrderByDateDesc();
        return convertToDto(marketInterestRate);
    }

    public void fetchAndSaveMarketInterestRate() {
        RestTemplate restTemplate = new RestTemplate();
        String startDate = LocalDate.now().withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String endDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String apiUrl = String.format("https://ecos.bok.or.kr/api/StatisticSearch/QMBA9ZYOT7J5XBSEMTJ0/json/kr/1/1000/721Y001/%s/%s", startDate, endDate);

        ResponseDto response = restTemplate.getForObject(apiUrl, ResponseDto.class);

        if (response != null && response.getStatisticSearch() != null && !response.getStatisticSearch().isEmpty()) {
            double rate = response.getStatisticSearch().get(0).getDataValue();
            MarketInterestRate marketInterestRate = new MarketInterestRate();
            marketInterestRate.setRate(rate);
            marketInterestRate.setDate(LocalDate.now());
            marketInterestRateRepository.save(marketInterestRate);
        }
    }

    private MarketInterestRate convertToEntity(MarketInterestRateDto dto) {
        MarketInterestRate marketInterestRate = new MarketInterestRate();
        marketInterestRate.setId(dto.getId());
        marketInterestRate.setRate(dto.getRate());
        marketInterestRate.setDate(dto.getDate());
        return marketInterestRate;
    }

    private MarketInterestRateDto convertToDto(MarketInterestRate entity) {
        MarketInterestRateDto dto = new MarketInterestRateDto();
        dto.setId(entity.getId());
        dto.setRate(entity.getRate());
        dto.setDate(entity.getDate());
        return dto;
    }
}