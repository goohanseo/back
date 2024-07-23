package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.CreditScore;
import com.example.p2p_loan_platform.application.domain.repository.CreditScoreRepository;
import com.example.p2p_loan_platform.application.dto.CreditScoreDto;
import com.example.p2p_loan_platform.application.dto.DebtorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    @Autowired
    private DebtorService debtorService;

    // 신용 점수 조회
    public Optional<CreditScoreDto> getCreditScoreByDebtorId(Long debtorId) {
        return creditScoreRepository.findByDebtorId(debtorId).map(this::convertToDto);
    }

    // 신용 점수 갱신
    public CreditScoreDto updateCreditScore(CreditScoreDto creditScoreDto) {
        CreditScore creditScore = convertToEntity(creditScoreDto);
        return convertToDto(creditScoreRepository.save(creditScore));
    }

    private CreditScore convertToEntity(CreditScoreDto creditScoreDto) {
        CreditScore creditScore = new CreditScore();
        creditScore.setId(creditScoreDto.getId());
        creditScore.setDebtor(debtorService.convertToEntity(creditScoreDto.getDebtor()));
        creditScore.setScore(creditScoreDto.getScore());
        creditScore.setUpdatedDate(creditScoreDto.getUpdatedDate());
        return creditScore;
    }

    private CreditScoreDto convertToDto(CreditScore creditScore) {
        CreditScoreDto creditScoreDto = new CreditScoreDto();
        creditScoreDto.setId(creditScore.getId());
        creditScoreDto.setDebtor(debtorService.convertToDto(creditScore.getDebtor()));
        creditScoreDto.setScore(creditScore.getScore());
        creditScoreDto.setUpdatedDate(creditScore.getUpdatedDate());
        return creditScoreDto;
    }
}
