package com.banking.business.rules;

import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.IndividualCustomerLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class IndividualCustomerLoanTypeBusinessRules {
    private final IndividualCustomerLoanTypeRepository repository;

    public void checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Individual customer loan type not found");
        }
    }

    public void validateLoanAmount(BigDecimal minAmount, BigDecimal maxAmount) {
        if (minAmount.compareTo(maxAmount) >= 0) {
            throw new BusinessException("Minimum amount must be less than maximum amount");
        }
    }

    public void validateTermRange(Integer minTerm, Integer maxTerm) {
        if (minTerm >= maxTerm) {
            throw new BusinessException("Minimum term must be less than maximum term");
        }
    }

    public void validateCreditScore(Integer minCreditScore) {
        if (minCreditScore < 0 || minCreditScore > 1000) {
            throw new BusinessException("Credit score must be between 0 and 1000");
        }
    }

    public void validateDebtToIncomeRatio(BigDecimal maxDebtToIncomeRatio) {
        if (maxDebtToIncomeRatio.compareTo(BigDecimal.ZERO) <= 0 || maxDebtToIncomeRatio.compareTo(BigDecimal.ONE) > 0) {
            throw new BusinessException("Debt to income ratio must be between 0 and 1");
        }
    }
} 