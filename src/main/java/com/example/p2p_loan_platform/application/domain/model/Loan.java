package com.example.p2p_loan_platform.application.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

    private BigDecimal amount;
    private String status;
    private BigDecimal interestRate;
    private int duration;

    // Getters and Setters
}