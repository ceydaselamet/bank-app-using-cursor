package com.banking.business.rules;

import com.banking.business.constants.Messages.BusinessMessages;
import com.banking.business.constants.Messages.BusinessRules;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualCustomerBusinessRules {
    
    private final IndividualCustomerRepository individualCustomerRepository;

    public void checkIfNationalIdExists(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new BusinessException(BusinessMessages.Individual.NATIONAL_ID_EXISTS);
        }
    }

    public void checkIfEmailExists(String email) {
        if (individualCustomerRepository.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.Common.EMAIL_EXISTS);
        }
    }

    public void checkIfCustomerNumberExists(String customerNumber) {
        if (individualCustomerRepository.existsByCustomerNumber(customerNumber)) {
            throw new BusinessException(BusinessMessages.Common.CUSTOMER_NUMBER_EXISTS);
        }
    }

    public void checkMinimumAge(int age) {
        if (age < BusinessRules.MINIMUM_INDIVIDUAL_AGE) {
            throw new BusinessException(BusinessMessages.Individual.MINIMUM_AGE_NOT_MET);
        }
    }
} 