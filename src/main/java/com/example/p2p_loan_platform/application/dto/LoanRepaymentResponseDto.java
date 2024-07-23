package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRepaymentResponseDto {
    private double monthlyRepaymentAmount;
    private int repaymentPeriod;
    private Long principalAmount; // 만기일시상환 시 필요한 원금
}
