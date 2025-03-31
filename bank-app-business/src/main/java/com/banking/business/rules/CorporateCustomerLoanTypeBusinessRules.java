package com.banking.business.rules;

import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CorporateCustomerLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CorporateCustomerLoanTypeBusinessRules {
    private final CorporateCustomerLoanTypeRepository repository;

    public void checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Corporate customer loan type not found");
        }
    }

    public void validateAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        if (minAmount.compareTo(maxAmount) >= 0) {
            throw new BusinessException("Minimum amount must be less than maximum amount");
        }
    }

    public void validateTermRange(Integer minTerm, Integer maxTerm) {
        if (minTerm >= maxTerm) {
            throw new BusinessException("Minimum term must be less than maximum term");
        }
    }

    public void validateYearsInBusiness(Integer minYearsInBusiness) {
        if (minYearsInBusiness < 0) {
            throw new BusinessException("Years in business cannot be negative");
        }
    }

    public void validateAnnualRevenue(BigDecimal minAnnualRevenue) {
        if (minAnnualRevenue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Minimum annual revenue must be positive");
        }
    }
} 