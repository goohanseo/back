package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.CreditScoreDto;
import com.example.p2p_loan_platform.application.service.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/credit-score")
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    @GetMapping("/{debtorId}")
    public ResponseEntity<CreditScoreDto> getCreditScoreByDebtorId(@PathVariable Long debtorId) {
        Optional<CreditScoreDto> creditScoreDto = creditScoreService.getCreditScoreByDebtorId(debtorId);
        return creditScoreDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public CreditScoreDto updateCreditScore(@RequestBody CreditScoreDto creditScoreDto) {
        return creditScoreService.updateCreditScore(creditScoreDto);
    }
}
