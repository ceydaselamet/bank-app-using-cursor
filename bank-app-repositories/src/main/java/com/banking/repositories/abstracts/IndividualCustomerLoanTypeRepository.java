package com.banking.repositories.abstracts;

import com.banking.entities.IndividualCustomerLoanType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualCustomerLoanTypeRepository extends JpaRepository<IndividualCustomerLoanType, Long> {
    List<IndividualCustomerLoanType> findByCustomer_Id(Long customerId);
    Page<IndividualCustomerLoanType> findByCustomer_Id(Long customerId, Pageable pageable);
    List<IndividualCustomerLoanType> findByMinCreditScoreLessThanEqual(Integer creditScore);
    Page<IndividualCustomerLoanType> findByMinCreditScoreLessThanEqual(Integer creditScore, Pageable pageable);
} 