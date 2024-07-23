package com.example.p2p_loan_platform.application.dto;

import com.example.p2p_loan_platform.application.domain.model.Debtor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreditScoreDto {
    private Long id;
    private DebtorDto debtor;
    private int score;
    private LocalDate updatedDate;
}