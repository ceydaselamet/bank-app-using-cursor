package com.banking.repositories.abstracts;

import com.banking.entities.LoanApplication;
import com.banking.enums.LoanApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    @Query("SELECT l FROM LoanApplication l WHERE " +
           "(l.individualCustomer.id = :customerId OR l.corporateCustomer.id = :customerId) " +
           "AND l.status = :status")
    List<LoanApplication> findByCustomerIdAndStatus(@Param("customerId") Long customerId, 
                                                  @Param("status") LoanApplicationStatus status);

    @Query("SELECT l FROM LoanApplication l WHERE " +
           "l.individualCustomer.id = :customerId OR l.corporateCustomer.id = :customerId")
    List<LoanApplication> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT l FROM LoanApplication l WHERE " +
           "l.individualCustomer.id = :customerId OR l.corporateCustomer.id = :customerId")
    Page<LoanApplication> findByCustomerId(@Param("customerId") Long customerId, Pageable pageable);
} 