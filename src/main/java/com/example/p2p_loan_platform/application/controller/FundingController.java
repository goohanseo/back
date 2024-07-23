package com.example.p2p_loan_platform.application.controller;

import com.example.p2p_loan_platform.application.dto.FundingDto;
import com.example.p2p_loan_platform.application.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funding")
public class FundingController {

    @Autowired
    private FundingService fundingService;

    @GetMapping
    public List<FundingDto> getAllFundings() {
        return fundingService.getAllFundings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundingDto> getFundingById(@PathVariable Long id) {
        Optional<FundingDto> fundingDto = fundingService.getFundingById(id);
        return fundingDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FundingDto createFunding(@RequestBody FundingDto fundingDto) {
        return fundingService.saveFunding(fundingDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<FundingDto> updateFunding(@PathVariable Long id, @RequestBody FundingDto fundingDto) {
//        if (!fundingService.getFundingById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        fundingDto.setId(id);
//        return ResponseEntity.ok(fundingService.saveFunding(fundingDto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunding(@PathVariable Long id) {
        if (!fundingService.getFundingById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        fundingService.deleteFunding(id);
        return ResponseEntity.noContent().build();
    }
}