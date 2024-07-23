package com.example.p2p_loan_platform.application.domain.repository;

import com.example.p2p_loan_platform.application.domain.model.MarketInterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketInterestRateRepository extends JpaRepository<MarketInterestRate, Long> {
    MarketInterestRate findTopByOrderByDateDesc();
}
