package com.banking.business.abstracts;

import com.banking.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByCustomerNumber(String customerNumber);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAllActive();
    String generateCustomerNumber(String prefix);
    void checkIfCustomerExists(Long id);
} 