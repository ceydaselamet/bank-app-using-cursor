package com.banking.business.rules;

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
            throw new BusinessException("Corporate customer with this tax number already exists");
        }
    }

    public void checkIfEmailExists(String email) {
        if (corporateCustomerRepository.existsByEmail(email)) {
            throw new BusinessException("Customer with this email already exists");
        }
    }

    public void checkIfCustomerNumberExists(String customerNumber) {
        if (corporateCustomerRepository.existsByCustomerNumber(customerNumber)) {
            throw new BusinessException("Customer with this customer number already exists");
        }
    }

    public void checkMinimumCompanyAge(int years) {
        if (years < 2) {
            throw new BusinessException("Company must be at least 2 years old");
        }
    }

    public void checkMinimumAnnualRevenue(Double annualRevenue) {
        if (annualRevenue < 1_000_000) {
            throw new BusinessException("Company must have minimum 1,000,000 annual revenue");
        }
    }
} 