package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class LoanDto {
    private Long id;
    private Long debtorId;
    private BigDecimal amount;
    private String status;
    private BigDecimal interestRate;
    private int duration;

    // Getters and Setters
}