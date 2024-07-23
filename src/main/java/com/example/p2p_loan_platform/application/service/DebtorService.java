package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.CreditScore;
import com.example.p2p_loan_platform.application.domain.model.Debtor;
import com.example.p2p_loan_platform.application.domain.model.MarketInterestRate;
import com.example.p2p_loan_platform.application.domain.repository.CreditScoreRepository;
import com.example.p2p_loan_platform.application.domain.repository.DebtorRepository;
import com.example.p2p_loan_platform.application.domain.repository.MarketInterestRateRepository;
import com.example.p2p_loan_platform.application.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DebtorService {

    @Autowired
    private DebtorRepository debtorRepository;
    @Autowired
    private CreditScoreRepository creditScoreRepository;
    @Autowired
    private MarketInterestRateRepository marketInterestRateRepository;


    // 소상공인 회원가입
    public DebtorDto saveDebtor(DebtorDto debtorDto) {
        Debtor debtor = convertToEntity(debtorDto);
        return convertToDto(debtorRepository.save(debtor));
    }

    // 모든 소상공인 조회
    public List<DebtorDto> getAllDebtors() {
        return debtorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // ID로 소상공인 조회
    public Optional<DebtorDto> getDebtorById(Long id) {
        return debtorRepository.findById(id).map(this::convertToDto);
    }

    // ID로 소상공인 삭제
    public void deleteDebtor(Long id) {
        debtorRepository.deleteById(id);
    }

    // 이메일과 비밀번호로 로그인
    public Optional<DebtorDto> login(LoginRequestDto loginRequestDto) {
        Optional<Debtor> debtor = debtorRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return debtor.map(this::convertToDto);
    }
    //대출 가능 금액 조회
    public Long calculateLoanableAmount(Long debtorId) {
        Optional<Debtor> debtorOptional = debtorRepository.findById(debtorId);
        if (debtorOptional.isPresent()) {
            Debtor debtor = debtorOptional.get();
            CreditScore creditScore = creditScoreRepository.findByDebtorId(debtorId).orElseThrow(() -> new RuntimeException("Credit score not found"));
            int score = creditScore.getScore(); // 종합등급
            Long revenue = debtor.getRevenue();

            // 대출 가능 금액 계산
            Long loanableAmount = revenue * getMultiplierByScore(score);

            // Debtor 엔티티에 대출 가능 금액 저장
            debtor.setLoanableAmount(loanableAmount);
            debtorRepository.save(debtor);

            return loanableAmount;
        } else {
            throw new RuntimeException("Debtor not found");
        }
    }

    private int getMultiplierByScore(int score) {
        switch (score) {
            case 1:
                return 100;
            case 2:
                return 90;
            case 3:
                return 80;
            case 4:
                return 70;
            default:
                throw new IllegalArgumentException("Invalid score");
        }
    }

    //대출상환금액, 기간 산출
    public LoanRepaymentResponseDto calculateLoanRepayment(LoanRepaymentRequestDto requestDto) {
        Long loanAmount = requestDto.getLoanAmount();
        int repaymentPeriod = requestDto.getRepaymentPeriod();
        String repaymentType = requestDto.getRepaymentType();

        // 최신 시장 금리 가져오기
        MarketInterestRate marketInterestRate = marketInterestRateRepository.findTopByOrderByDateDesc();
        double annualInterestRate = marketInterestRate.getRate();
        double monthlyInterestRate = annualInterestRate / 12 / 100;

        LoanRepaymentResponseDto responseDto = new LoanRepaymentResponseDto();
        responseDto.setRepaymentPeriod(repaymentPeriod);

        if ("만기일시상환".equals(repaymentType)) {
            double monthlyInterestPayment = loanAmount * monthlyInterestRate;
            responseDto.setMonthlyRepaymentAmount(monthlyInterestPayment);
            responseDto.setPrincipalAmount(loanAmount); // 만기일시상환 시 원금 반환
        } else if ("원금균등상환".equals(repaymentType)) {
            double principalRepayment = loanAmount / repaymentPeriod;
            double monthlyRepayment = principalRepayment + (loanAmount - (principalRepayment * (repaymentPeriod - 1))) * monthlyInterestRate;
            responseDto.setMonthlyRepaymentAmount(monthlyRepayment);
        } else if ("원리금균등상환".equals(repaymentType)) {
            double monthlyRepayment = loanAmount * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, repaymentPeriod))
                    / (Math.pow(1 + monthlyInterestRate, repaymentPeriod) - 1);
            responseDto.setMonthlyRepaymentAmount(monthlyRepayment);
        }

        return responseDto;
    }

    public Debtor convertToEntity(DebtorDto debtorDto) {
        Debtor debtor = new Debtor();
        debtor.setId(debtorDto.getId());
        debtor.setPassword(debtorDto.getPassword());
        debtor.setEmail(debtorDto.getEmail());
        debtor.setBusinessName(debtorDto.getBusinessName());
        debtor.setBusinessType(debtorDto.getBusinessType());
        debtor.setRevenue(debtorDto.getRevenue());
        return debtor;
    }

    public DebtorDto convertToDto(Debtor debtor) {
        DebtorDto debtorDto = new DebtorDto();
        debtorDto.setId(debtor.getId());
        debtorDto.setPassword(debtor.getPassword());
        debtorDto.setEmail(debtor.getEmail());
        debtorDto.setBusinessName(debtor.getBusinessName());
        debtorDto.setBusinessType(debtor.getBusinessType());
        debtorDto.setRevenue(debtor.getRevenue());
        return debtorDto;
    }
}
