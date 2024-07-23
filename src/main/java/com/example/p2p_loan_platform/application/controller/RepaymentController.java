package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.RepaymentDto;
import com.example.p2p_loan_platform.application.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/repayment")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @GetMapping
    public List<RepaymentDto> getAllRepayments() {
        return repaymentService.getAllRepayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepaymentDto> getRepaymentById(@PathVariable Long id) {
        Optional<RepaymentDto> repaymentDto = repaymentService.getRepaymentById(id);
        return repaymentDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RepaymentDto createRepayment(@RequestBody RepaymentDto repaymentDto) {
        return repaymentService.saveRepayment(repaymentDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<RepaymentDto> updateRepayment(@PathVariable Long id, @RequestBody RepaymentDto repaymentDto) {
//        if (!repaymentService.getRepaymentById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        repaymentDto.setId(id);
//        return ResponseEntity.ok(repaymentService.saveRepayment(repaymentDto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepayment(@PathVariable Long id) {
        if (!repaymentService.getRepaymentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        repaymentService.deleteRepayment(id);
        return ResponseEntity.noContent().build();
    }
}