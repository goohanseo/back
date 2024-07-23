package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class FundingDto {
    private Long id;
    private Long creditorId;
    private Long loanId;
    private BigDecimal amount;
    private String status;
    private BigDecimal refundAmount;

    // Getters and Setters
}