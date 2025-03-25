package com.banking.repositories.abstracts;

import com.banking.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IndividualCustomerRepository extends BaseRepository<IndividualCustomer, Long> {
    
    Optional<IndividualCustomer> findByNationalId(String nationalId);
    
    boolean existsByNationalId(String nationalId);
    
    @Query("SELECT ic FROM IndividualCustomer ic WHERE ic.creditScore >= :minScore")
    List<IndividualCustomer> findCustomersWithMinCreditScore(@Param("minScore") Integer minScore);
    
    @Query("SELECT ic FROM IndividualCustomer ic WHERE ic.monthlyIncome >= :minIncome AND ic.monthlyIncome <= :maxIncome")
    List<IndividualCustomer> findCustomersByIncomeRange(
            @Param("minIncome") Double minIncome,
            @Param("maxIncome") Double maxIncome
    );
} 