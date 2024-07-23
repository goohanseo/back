package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.CreditorDto;
import com.example.p2p_loan_platform.application.dto.DebtorDto;
import com.example.p2p_loan_platform.application.dto.LoginRequestDto;
import com.example.p2p_loan_platform.application.service.CreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/creditor")
public class CreditorController {

    @Autowired
    private CreditorService creditorService;

    @GetMapping
    public List<CreditorDto> getAllCreditors() {
        return creditorService.getAllCreditors();
    }


    //회원가입
    @PostMapping
    public CreditorDto createCreditor(@RequestBody CreditorDto creditorDto) {
        return creditorService.saveCreditor(creditorDto);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<CreditorDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<CreditorDto> creditorDto = creditorService.login(loginRequestDto);
        return creditorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<CreditorDto> updateCreditor(@PathVariable Long id, @RequestBody CreditorDto creditorDto) {
//        if (!creditorService.getCreditorById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        creditorDto.setId(id);
//        return ResponseEntity.ok(creditorService.saveCreditor(creditorDto));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditorDto> getCreditorById(@PathVariable Long id) {
        Optional<CreditorDto> creditorDto = creditorService.getCreditorById(id);
        return creditorDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditor(@PathVariable Long id) {
        if (!creditorService.getCreditorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        creditorService.deleteCreditor(id);
        return ResponseEntity.noContent().build();
    }
}