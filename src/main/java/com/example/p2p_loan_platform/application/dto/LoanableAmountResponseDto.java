package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanableAmountResponseDto {
    private Long debtorId;
    private Long loanableAmount;
}