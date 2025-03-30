package com.banking.business.abstracts;

import com.banking.business.dtos.requests.corporate.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.corporate.CorporateCustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CorporateCustomerService {
    CorporateCustomerResponse add(CreateCorporateCustomerRequest request);
    CorporateCustomerResponse getById(Long id);
    CorporateCustomerResponse getByTaxNumber(String taxNumber);
    List<CorporateCustomerResponse> getAll();
    List<CorporateCustomerResponse> getAllByTaxOffice(String taxOffice);
    List<CorporateCustomerResponse> getAllByMinAnnualRevenue(Double minRevenue);
    List<CorporateCustomerResponse> getAllByCompanySizeAndCreditRating(Integer minSize, String rating);
    void delete(Long id);
    Page<CorporateCustomerResponse> getAllPaginated(Pageable pageable);
} 