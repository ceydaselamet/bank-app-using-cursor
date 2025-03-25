package com.banking.repositories.abstracts;

import com.banking.entities.CorporateCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CorporateCustomerRepository extends BaseRepository<CorporateCustomer, Long> {
    
    Optional<CorporateCustomer> findByTaxNumber(String taxNumber);
    
    boolean existsByTaxNumber(String taxNumber);
    
    List<CorporateCustomer> findByTaxOffice(String taxOffice);
    
    @Query("SELECT cc FROM CorporateCustomer cc WHERE cc.annualRevenue >= :minRevenue")
    List<CorporateCustomer> findCustomersWithMinAnnualRevenue(@Param("minRevenue") Double minRevenue);
    
    @Query("SELECT cc FROM CorporateCustomer cc WHERE cc.companySize >= :minSize AND cc.creditRating = :rating")
    List<CorporateCustomer> findByCompanySizeAndCreditRating(
            @Param("minSize") Integer minSize,
            @Param("rating") String rating
    );
} 