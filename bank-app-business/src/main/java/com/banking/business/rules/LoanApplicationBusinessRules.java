package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.entities.LoanApplication;
import com.banking.entities.LoanType;
import com.banking.enums.CustomerType;
import com.banking.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.LoanApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class LoanApplicationBusinessRules {
    private final LoanApplicationRepository repository;

    public void checkIfLoanApplicationExists(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.LoanApplication.NOT_FOUND);
        }
    }

    public void validateLoanAmount(LoanType loanType, BigDecimal amount) {
        if (amount.compareTo(loanType.getMinAmount()) < 0 || amount.compareTo(loanType.getMaxAmount()) > 0) {
            throw new BusinessException(Messages.LoanApplication.INVALID_AMOUNT);
        }
    }

    public void validateLoanTerm(LoanType loanType, Integer term) {
        if (term < loanType.getMinTerm() || term > loanType.getMaxTerm()) {
            throw new BusinessException(Messages.LoanApplication.INVALID_TERM);
        }
    }

    public void validateCustomerType(LoanType loanType, CustomerType customerType) {
        if (loanType.getCustomerType() != customerType) {
            throw new BusinessException(Messages.LoanApplication.INVALID_CUSTOMER_TYPE);
        }
    }

    public void checkIfCanBeApprovedOrRejected(LoanApplication application) {
        if (application.getStatus() != LoanApplicationStatus.PENDING) {
            throw new BusinessException(Messages.LoanApplication.ALREADY_PROCESSED);
        }
    }
} 