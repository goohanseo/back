package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.*;
import com.example.p2p_loan_platform.application.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/debtor")
public class DebtorController {

    @Autowired
    private DebtorService debtorService;

    @GetMapping
    public List<DebtorDto> getAllDebtors() {
        return debtorService.getAllDebtors();
    }

    //회원가입
    @PostMapping
    public DebtorDto createDebtor(@RequestBody DebtorDto debtorDto) {
        return debtorService.saveDebtor(debtorDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<DebtorDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<DebtorDto> debtorDto = debtorService.login(loginRequestDto);
        return debtorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }
    //대출가능금액 조회
    @GetMapping("/loanable-amount/{debtorId}")
    public ResponseEntity<Long> getLoanableAmount(@PathVariable Long debtorId) {
        Long loanableAmount = debtorService.calculateLoanableAmount(debtorId);
        return ResponseEntity.ok(loanableAmount);
    }
    //대출상환금액, 기간산출
    @PostMapping("/calculate-repayment")
    public ResponseEntity<LoanRepaymentResponseDto> calculateRepayment(@RequestBody LoanRepaymentRequestDto requestDto) {
        LoanRepaymentResponseDto responseDto = debtorService.calculateLoanRepayment(requestDto);
        return ResponseEntity.ok(responseDto);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<DebtorDto> updateDebtor(@PathVariable Long id, @RequestBody DebtorDto debtorDto) {
//        if (!debtorService.getDebtorById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        debtorDto.setId(id);
//        return ResponseEntity.ok(debtorService.saveDebtor(debtorDto));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtorDto> getDebtorById(@PathVariable Long id) {
        Optional<DebtorDto> debtorDto = debtorService.getDebtorById(id);
        return debtorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebtor(@PathVariable Long id) {
        if (!debtorService.getDebtorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        debtorService.deleteDebtor(id);
        return ResponseEntity.noContent().build();
    }
}