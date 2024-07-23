package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.Funding;
import com.example.p2p_loan_platform.application.domain.repository.FundingRepository;
import com.example.p2p_loan_platform.application.dto.FundingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FundingService {

    @Autowired
    private FundingRepository fundingRepository;

    /**
     * 모든 펀딩을 조회합니다.
     *
     * @return 펀딩 DTO 목록
     */
    public List<FundingDto> getAllFundings() {
        return fundingRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * ID로 특정 펀딩을 조회합니다.
     *
     * @param id 펀딩 ID
     * @return 펀딩 DTO
     */
    public Optional<FundingDto> getFundingById(Long id) {
        return fundingRepository.findById(id).map(this::convertToDto);
    }

    /**
     * 새로운 펀딩을 생성하거나 기존 펀딩을 업데이트합니다.
     *
     * @param fundingDto 펀딩 DTO
     * @return 저장된 펀딩 DTO
     */
    public FundingDto saveFunding(FundingDto fundingDto) {
        Funding funding = convertToEntity(fundingDto);
        return convertToDto(fundingRepository.save(funding));
    }

    /**
     * ID로 특정 펀딩을 삭제합니다.
     *
     * @param id 펀딩 ID
     */
    public void deleteFunding(Long id) {
        fundingRepository.deleteById(id);
    }

    /**
     * Funding 엔티티를 FundingDto로 변환합니다.
     *
     * @param funding 펀딩 엔티티
     * @return 펀딩 DTO
     */
    private FundingDto convertToDto(Funding funding) {
        FundingDto dto = new FundingDto();
        dto.setId(funding.getId());
        dto.setCreditorId(funding.getCreditor().getId());
        dto.setLoanId(funding.getLoan().getId());
        dto.setAmount(funding.getAmount());
        dto.setStatus(funding.getStatus());
        dto.setRefundAmount(funding.getRefundAmount());
        return dto;
    }

    /**
     * FundingDto를 Funding 엔티티로 변환합니다.
     *
     * @param dto 펀딩 DTO
     * @return 펀딩 엔티티
     */
    private Funding convertToEntity(FundingDto dto) {
        Funding funding = new Funding();
        funding.setId(dto.getId());
        funding.setAmount(dto.getAmount());
        funding.setStatus(dto.getStatus());
        funding.setRefundAmount(dto.getRefundAmount());
        return funding;
    }
}