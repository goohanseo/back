package com.example.p2p_loan_platform.application.domain.repository;

import com.example.p2p_loan_platform.application.domain.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {
}