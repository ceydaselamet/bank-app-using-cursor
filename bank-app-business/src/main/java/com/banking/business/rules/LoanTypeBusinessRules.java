package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.entities.LoanType;
import com.banking.repositories.abstracts.LoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class LoanTypeBusinessRules {
    private final LoanTypeRepository repository;

    public void checkIfLoanTypeExists(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.LoanType.NOT_FOUND);
        }
    }

    public void checkIfParentLoanTypeExists(Long parentId) {
        if (parentId != null && !repository.existsById(parentId)) {
            throw new BusinessException(Messages.LoanType.PARENT_NOT_FOUND);
        }
    }

    public void validateAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        if (minAmount.compareTo(maxAmount) > 0) {
            throw new BusinessException(Messages.LoanType.INVALID_AMOUNT_RANGE);
        }
    }

    public void validateTermRange(Integer minTerm, Integer maxTerm) {
        if (minTerm > maxTerm) {
            throw new BusinessException(Messages.LoanType.INVALID_TERM_RANGE);
        }
    }
} 