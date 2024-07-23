package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDto {
    private Long id;
    private Long debtorId;
    private Double fundingAmount;
    private Integer period;
    private Double marketInterestRate;
    private Double expectedInvestorInterestRate;
}
