package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.Loan;
import com.example.p2p_loan_platform.application.domain.repository.LoanRepository;
import com.example.p2p_loan_platform.application.dto.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    /**
     * 모든 대출을 조회합니다.
     *
     * @return 대출 DTO 목록
     */
    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * ID로 특정 대출을 조회합니다.
     *
     * @param id 대출 ID
     * @return 대출 DTO
     */
    public Optional<LoanDto> getLoanById(Long id) {
        return loanRepository.findById(id).map(this::convertToDto);
    }

    /**
     * 새로운 대출을 생성하거나 기존 대출을 업데이트합니다.
     *
     * @param loanDto 대출 DTO
     * @return 저장된 대출 DTO
     */
    public LoanDto saveLoan(LoanDto loanDto) {
        Loan loan = convertToEntity(loanDto);
        return convertToDto(loanRepository.save(loan));
    }

    /**
     * ID로 특정 대출을 삭제합니다.
     *
     * @param id 대출 ID
     */
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    /**
     * Loan 엔티티를 LoanDto로 변환합니다.
     *
     * @param loan 대출 엔티티
     * @return 대출 DTO
     */
    private LoanDto convertToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setDebtorId(loan.getDebtor().getId());
        dto.setAmount(loan.getAmount());
        dto.setStatus(loan.getStatus());
        dto.setInterestRate(loan.getInterestRate());
        dto.setDuration(loan.getDuration());
        return dto;
    }

    /**
     * LoanDto를 Loan 엔티티로 변환합니다.
     *
     * @param dto 대출 DTO
     * @return 대출 엔티티
     */
    private Loan convertToEntity(LoanDto dto) {
        Loan loan = new Loan();
        loan.setId(dto.getId());
        loan.setAmount(dto.getAmount());
        loan.setStatus(dto.getStatus());
        loan.setInterestRate(dto.getInterestRate());
        loan.setDuration(dto.getDuration());
        return loan;
    }
}
