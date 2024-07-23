package com.example.p2p_loan_platform.application.domain.repository;

import com.example.p2p_loan_platform.application.domain.model.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long> {
    Optional<Creditor> findByEmailAndPassword(String email, String password);
}