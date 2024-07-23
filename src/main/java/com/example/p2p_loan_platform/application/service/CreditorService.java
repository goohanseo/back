package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.Creditor;
import com.example.p2p_loan_platform.application.domain.model.Debtor;
import com.example.p2p_loan_platform.application.domain.repository.CreditorRepository;
import com.example.p2p_loan_platform.application.dto.CreditorDto;
import com.example.p2p_loan_platform.application.dto.DebtorDto;
import com.example.p2p_loan_platform.application.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditorService {

    @Autowired
    private CreditorRepository creditorRepository;
    //투자자 회원가입
    public CreditorDto saveCreditor(CreditorDto creditorDto) {
        Creditor creditor = convertToEntity(creditorDto);
        return convertToDto(creditorRepository.save(creditor));
    }

    //로그인
    public Optional<CreditorDto> login(LoginRequestDto loginRequestDto) {
        Optional<Creditor> creditor = creditorRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return creditor.map(this::convertToDto);
    }

    /**
     * 모든 투자자를 조회합니다.
     *
     * @return 투자자 DTO 목록
     */
    public List<CreditorDto> getAllCreditors() {
        return creditorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * ID로 특정 투자자를 조회합니다.
     *
     * @param id 투자자 ID
     * @return 투자자 DTO
     */
    public Optional<CreditorDto> getCreditorById(Long id) {
        return creditorRepository.findById(id).map(this::convertToDto);
    }

    /**
     * 새로운 투자자를 생성하거나 기존 투자자를 업데이트합니다.
     *
     * @param creditorDto 투자자 DTO
     * @return 저장된 투자자 DTO
     */


    /**
     * ID로 특정 투자자를 삭제합니다.
     *
     * @param id 투자자 ID
     */
    public void deleteCreditor(Long id) {
        creditorRepository.deleteById(id);
    }

    /**
     * Creditor 엔티티를 CreditorDto로 변환합니다.
     *
     * @param creditor 투자자 엔티티
     * @return 투자자 DTO
     */
    private CreditorDto convertToDto(Creditor creditor) {
        CreditorDto dto = new CreditorDto();
        dto.setId(creditor.getId());
        dto.setPassword(creditor.getPassword());
        dto.setEmail(creditor.getEmail());
        return dto;
    }

    /**
     * CreditorDto를 Creditor 엔티티로 변환합니다.
     *
     * @param dto 투자자 DTO
     * @return 투자자 엔티티
     */
    private Creditor convertToEntity(CreditorDto dto) {
        Creditor creditor = new Creditor();
        creditor.setId(dto.getId());
        creditor.setPassword(dto.getPassword());
        creditor.setEmail(dto.getEmail());
        return creditor;
    }
}