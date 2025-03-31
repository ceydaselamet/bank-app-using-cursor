package com.banking.repositories.abstracts;

import com.banking.entities.CorporateCustomerLoanType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CorporateCustomerLoanTypeRepository extends JpaRepository<CorporateCustomerLoanType, Long> {
    List<CorporateCustomerLoanType> findByCustomer_Id(Long customerId);
    Page<CorporateCustomerLoanType> findByCustomer_Id(Long customerId, Pageable pageable);
    List<CorporateCustomerLoanType> findByMinAnnualRevenueLessThanEqual(BigDecimal annualRevenue);
    Page<CorporateCustomerLoanType> findByMinAnnualRevenueLessThanEqual(BigDecimal annualRevenue, Pageable pageable);
    List<CorporateCustomerLoanType> findByMinYearsInBusinessLessThanEqual(Integer yearsInBusiness);
    Page<CorporateCustomerLoanType> findByMinYearsInBusinessLessThanEqual(Integer yearsInBusiness, Pageable pageable);
} 