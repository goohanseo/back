package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.LoanDto;
import com.example.p2p_loan_platform.application.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<LoanDto> applyForLoan(@RequestBody LoanDto loanDto) {
        LoanDto appliedLoan = loanService.applyForLoan(loanDto);
        return ResponseEntity.ok(appliedLoan);
    }
}
