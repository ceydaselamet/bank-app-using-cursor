package com.banking.repositories.abstracts;

import com.banking.entities.LoanType;
import com.banking.enums.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {
    List<LoanType> findByCustomerType(CustomerType customerType);
    List<LoanType> findByParentLoanTypeIsNull();
    List<LoanType> findByParentLoanType_Id(Long parentId);
} 