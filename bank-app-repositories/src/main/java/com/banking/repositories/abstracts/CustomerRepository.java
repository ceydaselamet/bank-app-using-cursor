package com.banking.repositories.abstracts;

import com.banking.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Long> {
    
    Optional<Customer> findByCustomerNumber(String customerNumber);
    
    Optional<Customer> findByEmail(String email);
    
    boolean existsByCustomerNumber(String customerNumber);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber AND c.isActive = true")
    Optional<Customer> findByPhoneNumberAndActive(@Param("phoneNumber") String phoneNumber);
} 