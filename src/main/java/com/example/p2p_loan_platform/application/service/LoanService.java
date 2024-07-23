package com.example.p2p_loan_platform.application.service;

import com.example.p2p_loan_platform.application.domain.model.Debtor;
import com.example.p2p_loan_platform.application.domain.model.Loan;
import com.example.p2p_loan_platform.application.domain.repository.DebtorRepository;
import com.example.p2p_loan_platform.application.domain.repository.LoanRepository;
import com.example.p2p_loan_platform.application.domain.repository.MarketInterestRateRepository;
import com.example.p2p_loan_platform.application.dto.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MarketInterestRateRepository marketInterestRateRepository;

    @Autowired
    private DebtorRepository debtorRepository;

    public LoanDto applyForLoan(LoanDto loanDto) {
        double marketInterestRate = marketInterestRateRepository.findTopByOrderByDateDesc()
                .map(rate -> rate.getRate())
                .orElseThrow(() -> new RuntimeException("Market interest rate not found"));

        double expectedInvestorInterestRate = marketInterestRate - 2.0;

        Debtor debtor = debtorRepository.findById(loanDto.getDebtorId())
                .orElseThrow(() -> new RuntimeException("Debtor not found"));

        Loan loan = new Loan();
        loan.setDebtor(debtor);
        loan.setAmount(loanDto.getFundingAmount().doubleValue());
        loan.setDuration(loanDto.getPeriod());
        loan.setMarketInterestRate(marketInterestRate);
        loan.setExpectedInvestorInterestRate(expectedInvestorInterestRate);

        Loan savedLoan = loanRepository.save(loan);

        LoanDto savedLoanDto = new LoanDto();
        savedLoanDto.setId(savedLoan.getId());
        savedLoanDto.setDebtorId(savedLoan.getDebtor().getId());
        savedLoanDto.setFundingAmount(savedLoan.getAmount());
        savedLoanDto.setPeriod(savedLoan.getDuration());
        savedLoanDto.setMarketInterestRate(savedLoan.getMarketInterestRate());
        savedLoanDto.setExpectedInvestorInterestRate(savedLoan.getExpectedInvestorInterestRate());

        return savedLoanDto;
    }
}
