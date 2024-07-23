package com.example.p2p_loan_platform.application.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class RepaymentDto {
    private Long id;
    private Long loanId;
    private BigDecimal amount;
    private LocalDateTime repaymentDate;

    // Getters and Setters
}