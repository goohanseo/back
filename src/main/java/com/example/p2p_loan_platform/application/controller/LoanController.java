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

    @GetMapping
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long id) {
        Optional<LoanDto> loanDto = loanService.getLoanById(id);
        return loanDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LoanDto createLoan(@RequestBody LoanDto loanDto) {
        return loanService.saveLoan(loanDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long id, @RequestBody LoanDto loanDto) {
//        if (!loanService.getLoanById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        loanDto.setId(id);
//        return ResponseEntity.ok(loanService.saveLoan(loanDto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        if (!loanService.getLoanById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}