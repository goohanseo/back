package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanableAmountRequestDto {
    private Long debtorId;
    private Long revenue;
    private int grade;
}