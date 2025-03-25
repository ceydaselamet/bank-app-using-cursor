package com.banking.business.rules;

import com.banking.core.exceptions.BusinessException;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualCustomerBusinessRules {
    
    private final IndividualCustomerRepository individualCustomerRepository;

    public void checkIfNationalIdExists(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new BusinessException("Individual customer with this national ID already exists");
        }
    }

    public void checkIfEmailExists(String email) {
        if (individualCustomerRepository.existsByEmail(email)) {
            throw new BusinessException("Customer with this email already exists");
        }
    }

    public void checkIfCustomerNumberExists(String customerNumber) {
        if (individualCustomerRepository.existsByCustomerNumber(customerNumber)) {
            throw new BusinessException("Customer with this customer number already exists");
        }
    }

    public void checkMinimumAge(int age) {
        if (age < 18) {
            throw new BusinessException("Customer must be at least 18 years old");
        }
    }
} 