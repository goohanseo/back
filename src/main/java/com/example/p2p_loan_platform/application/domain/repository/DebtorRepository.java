package com.example.p2p_loan_platform.application.domain.repository;


import com.example.p2p_loan_platform.application.domain.model.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Long> {
    Optional<Debtor> findByEmailAndPassword(String email, String password);

}