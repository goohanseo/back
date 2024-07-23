package com.example.p2p_loan_platform.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DebtorDto {
    private Long id;
    private String password;
    private String email;
    private String businessName;
    private String businessType;
    private Long revenue;
    // 대출 가능 금액 필드 추가
    private Long loanableAmount;


    // Getters and Setters
}