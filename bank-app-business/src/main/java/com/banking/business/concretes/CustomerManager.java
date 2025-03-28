package com.banking.business.concretes;

import com.banking.business.abstracts.CustomerService;
import com.banking.business.constants.CustomerConstants;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.entities.Customer;
import com.banking.repositories.abstracts.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Random random = new Random();

    @Override
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAllActive() {
        return customerRepository.findAll().stream()
                .filter(Customer::isActive)
                .toList();
    }

    @Override
    public String generateCustomerNumber(String prefix) {
        String customerNumber;
        do {
            StringBuilder number = new StringBuilder(prefix);
            for (int i = 0; i < CustomerConstants.CUSTOMER_NUMBER_LENGTH - 1; i++) {
                number.append(random.nextInt(10));
            }
            customerNumber = number.toString();
        } while (customerRepository.existsByCustomerNumber(customerNumber));
        
        return customerNumber;
    }

    @Override
    public void checkIfCustomerExists(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new BusinessException(CustomerConstants.CUSTOMER_NOT_FOUND);
        }
    }
} 