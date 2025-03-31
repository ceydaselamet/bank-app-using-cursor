package com.banking.business.abstracts;

import com.banking.business.dtos.requests.loantypes.CreateCorporateCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetCorporateCustomerLoanTypeResponse;
import com.banking.core.utils.paging.PageDto;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CorporateCustomerLoanTypeService {
    GetCorporateCustomerLoanTypeResponse create(CreateCorporateCustomerLoanTypeRequest request);
    List<GetCorporateCustomerLoanTypeResponse> getAll();
    PageDto<GetCorporateCustomerLoanTypeResponse> getAllPaginated(Pageable pageable);
    GetCorporateCustomerLoanTypeResponse getById(Long id);
    List<GetCorporateCustomerLoanTypeResponse> getByCustomerId(Long customerId);
    PageDto<GetCorporateCustomerLoanTypeResponse> getByCustomerIdPaginated(Long customerId, Pageable pageable);
    List<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenue(BigDecimal annualRevenue);
    PageDto<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenuePaginated(BigDecimal annualRevenue, Pageable pageable);
    List<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusiness(Integer yearsInBusiness);
    PageDto<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusinessPaginated(Integer yearsInBusiness, Pageable pageable);
    void delete(Long id);
} 