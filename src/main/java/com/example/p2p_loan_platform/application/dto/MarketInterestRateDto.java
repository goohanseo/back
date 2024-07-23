package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarketInterestRateDto {
    private Long id;
    private double rate;
    private LocalDate date;
}