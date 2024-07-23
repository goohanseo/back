package com.example.p2p_loan_platform.application.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private String businessName;
    private String businessType;
    private Long revenue;
    // 대출 가능 금액 필드 추가
    private Long loanableAmount;

    @OneToOne(mappedBy = "debtor")
    @JsonManagedReference
    private CreditScore creditScore;
}