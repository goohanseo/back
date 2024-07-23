package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.Repayment;
import com.example.p2p_loan_platform.application.domain.repository.RepaymentRepository;
import com.example.p2p_loan_platform.application.dto.RepaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepository;

    /**
     * 모든 상환 내역을 조회합니다.
     *
     * @return 상환 DTO 목록
     */
    public List<RepaymentDto> getAllRepayments() {
        return repaymentRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * ID로 특정 상환 내역을 조회합니다.
     *
     * @param id 상환 ID
     * @return 상환 DTO
     */
    public Optional<RepaymentDto> getRepaymentById(Long id) {
        return repaymentRepository.findById(id).map(this::convertToDto);
    }

    /**
     * 새로운 상환 내역을 생성하거나 기존 상환 내역을 업데이트합니다.
     *
     * @param repaymentDto 상환 DTO
     * @return 저장된 상환 DTO
     */
    public RepaymentDto saveRepayment(RepaymentDto repaymentDto) {
        Repayment repayment = convertToEntity(repaymentDto);
        return convertToDto(repaymentRepository.save(repayment));
    }

    /**
     * ID로 특정 상환 내역을 삭제합니다.
     *
     * @param id 상환 ID
     */
    public void deleteRepayment(Long id) {
        repaymentRepository.deleteById(id);
    }

    /**
     * Repayment 엔티티를 RepaymentDto로 변환합니다.
     *
     * @param repayment 상환 엔티티
     * @return 상환 DTO
     */
    private RepaymentDto convertToDto(Repayment repayment) {
        RepaymentDto dto = new RepaymentDto();
        dto.setId(repayment.getId());
        dto.setLoanId(repayment.getLoan().getId());
        dto.setAmount(repayment.getAmount());
        dto.setRepaymentDate(repayment.getRepaymentDate());
        return dto;
    }

    /**
     * RepaymentDto를 Repayment 엔티티로 변환합니다.
     *
     * @param dto 상환 DTO
     * @return 상환 엔티티
     */
    private Repayment convertToEntity(RepaymentDto dto) {
        Repayment repayment = new Repayment();
        repayment.setId(dto.getId());
        repayment.setAmount(dto.getAmount());
        repayment.setRepaymentDate(dto.getRepaymentDate());
        return repayment;
    }
}