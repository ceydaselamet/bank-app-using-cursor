package com.banking.webapi.repositories;

import com.banking.entities.LoanApplication;
import com.banking.enums.LoanApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByIndividualCustomer_IdAndStatus(Long customerId, LoanApplicationStatus status);
    List<LoanApplication> findByCorporateCustomer_IdAndStatus(Long customerId, LoanApplicationStatus status);
    Page<LoanApplication> findByIndividualCustomer_Id(Long customerId, Pageable pageable);
    Page<LoanApplication> findByCorporateCustomer_Id(Long customerId, Pageable pageable);
} 