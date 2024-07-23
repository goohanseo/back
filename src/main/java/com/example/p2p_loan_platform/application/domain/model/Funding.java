package com.example.p2p_loan_platform.application.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creditor_id", nullable = false)
    private Creditor creditor;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    private BigDecimal amount;
    private String status;
    private BigDecimal refundAmount;

    // Getters and Setters
}