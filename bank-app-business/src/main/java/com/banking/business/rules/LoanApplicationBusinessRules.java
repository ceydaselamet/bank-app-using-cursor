package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.entities.*;
import com.banking.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.LoanApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor
public class LoanApplicationBusinessRules {
    private final LoanApplicationRepository repository;

    public void checkIfLoanApplicationExists(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.LoanApplication.NOT_FOUND);
        }
    }

    public void validateLoanAmount(IndividualCustomerLoanType loanType, BigDecimal amount) {
        if (amount.compareTo(loanType.getMinAmount()) < 0 || amount.compareTo(loanType.getMaxAmount()) > 0) {
            throw new BusinessException(Messages.LoanApplication.INVALID_AMOUNT);
        }
    }

    public void validateLoanAmount(CorporateCustomerLoanType loanType, BigDecimal amount) {
        if (amount.compareTo(loanType.getMinAmount()) < 0 || amount.compareTo(loanType.getMaxAmount()) > 0) {
            throw new BusinessException(Messages.LoanApplication.INVALID_AMOUNT);
        }
    }

    public void validateLoanTerm(IndividualCustomerLoanType loanType, Integer term) {
        if (term < loanType.getMinTerm() || term > loanType.getMaxTerm()) {
            throw new BusinessException(Messages.LoanApplication.INVALID_TERM);
        }
    }

    public void validateLoanTerm(CorporateCustomerLoanType loanType, Integer term) {
        if (term < loanType.getMinTerm() || term > loanType.getMaxTerm()) {
            throw new BusinessException(Messages.LoanApplication.INVALID_TERM);
        }
    }

    public void validateIndividualCustomerEligibility(IndividualCustomer customer, IndividualCustomerLoanType loanType, BigDecimal monthlyPayment) {
        validateCreditScore(customer, loanType);
        validateDebtToIncomeRatio(customer, monthlyPayment, loanType);
    }

    public void validateCorporateCustomerEligibility(CorporateCustomer customer, CorporateCustomerLoanType loanType) {
        validateYearsInBusiness(customer, loanType);
        validateAnnualRevenue(customer, loanType);
    }

    private void validateCreditScore(IndividualCustomer customer, IndividualCustomerLoanType loanType) {
        if (customer.getCreditScore() < loanType.getMinCreditScore()) {
            throw new BusinessException(Messages.LoanApplication.INSUFFICIENT_CREDIT_SCORE);
        }
    }

    private void validateDebtToIncomeRatio(IndividualCustomer customer, BigDecimal monthlyPayment, IndividualCustomerLoanType loanType) {
        var monthlyIncome = BigDecimal.valueOf(customer.getMonthlyIncome());
        var currentDebt = customer.getCurrentDebt() != null ? customer.getCurrentDebt() : BigDecimal.ZERO;
        var totalMonthlyDebt = currentDebt.add(monthlyPayment);
        var debtToIncomeRatio = totalMonthlyDebt.divide(monthlyIncome, 2, BigDecimal.ROUND_HALF_UP);

        if (debtToIncomeRatio.compareTo(loanType.getMaxDebtToIncomeRatio()) > 0) {
            throw new BusinessException(Messages.LoanApplication.EXCEEDS_DEBT_TO_INCOME_RATIO);
        }
    }

    private void validateYearsInBusiness(CorporateCustomer customer, CorporateCustomerLoanType loanType) {
        var yearsInBusiness = Period.between(customer.getEstablishmentDate(), LocalDate.now()).getYears();
        if (yearsInBusiness < loanType.getMinYearsInBusiness()) {
            throw new BusinessException(Messages.LoanApplication.INSUFFICIENT_BUSINESS_HISTORY);
        }
    }

    private void validateAnnualRevenue(CorporateCustomer customer, CorporateCustomerLoanType loanType) {
        if (customer.getAnnualRevenue().compareTo(loanType.getMinAnnualRevenue()) < 0) {
            throw new BusinessException(Messages.LoanApplication.INSUFFICIENT_ANNUAL_REVENUE);
        }
    }

    public void checkIfCanBeApprovedOrRejected(LoanApplication application) {
        if (application.getStatus() != LoanApplicationStatus.PENDING) {
            throw new BusinessException(Messages.LoanApplication.ALREADY_PROCESSED);
        }
    }
} 