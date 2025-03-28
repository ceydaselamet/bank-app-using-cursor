package com.banking.business.rules;

import com.banking.business.constants.Messages.BusinessMessages;
import com.banking.business.constants.Messages.BusinessRules;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorporateCustomerBusinessRules {
    
    private final CorporateCustomerRepository corporateCustomerRepository;

    public void checkIfTaxNumberExists(String taxNumber) {
        if (corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new BusinessException(BusinessMessages.Corporate.TAX_NUMBER_EXISTS);
        }
    }

    public void checkIfEmailExists(String email) {
        if (corporateCustomerRepository.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.Common.EMAIL_EXISTS);
        }
    }

    public void checkIfCustomerNumberExists(String customerNumber) {
        if (corporateCustomerRepository.existsByCustomerNumber(customerNumber)) {
            throw new BusinessException(BusinessMessages.Common.CUSTOMER_NUMBER_EXISTS);
        }
    }

    public void checkMinimumCompanyAge(int years) {
        if (years < BusinessRules.MINIMUM_COMPANY_AGE) {
            throw new BusinessException(BusinessMessages.Corporate.MINIMUM_COMPANY_AGE);
        }
    }

    public void checkMinimumAnnualRevenue(Double annualRevenue) {
        if (annualRevenue < BusinessRules.MINIMUM_ANNUAL_REVENUE) {
            throw new BusinessException(BusinessMessages.Corporate.MINIMUM_ANNUAL_REVENUE);
        }
    }
} 