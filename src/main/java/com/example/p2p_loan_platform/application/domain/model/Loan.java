package com.example.p2p_loan_platform.application.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "debtor_id", nullable = false)
    private Debtor debtor;

    private double amount;
    private String status;
    private double marketInterestRate;
    @Column(name = "expected_investor_interest_rate")
    private double expectedInvestorInterestRate;
    private int duration;
}
