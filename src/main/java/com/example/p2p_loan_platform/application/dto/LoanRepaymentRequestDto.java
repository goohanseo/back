package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRepaymentRequestDto {
    private Long debtorId;
    private Long loanAmount;
    private int repaymentPeriod; // 6 or 12 (months)
    private String repaymentType; // 만기일시상환, 원금균등상환, 원리금균등상환
}