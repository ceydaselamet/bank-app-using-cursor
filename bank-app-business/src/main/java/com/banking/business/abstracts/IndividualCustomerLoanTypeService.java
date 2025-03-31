package com.banking.business.abstracts;

import com.banking.business.dtos.requests.loantypes.CreateIndividualCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetIndividualCustomerLoanTypeResponse;
import com.banking.core.utils.paging.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IndividualCustomerLoanTypeService {
    GetIndividualCustomerLoanTypeResponse create(CreateIndividualCustomerLoanTypeRequest request);
    GetIndividualCustomerLoanTypeResponse getById(Long id);
    List<GetIndividualCustomerLoanTypeResponse> getAll();
    PageDto<GetIndividualCustomerLoanTypeResponse> getAllPaginated(Pageable pageable);
    List<GetIndividualCustomerLoanTypeResponse> getByCustomerId(Long customerId);
    PageDto<GetIndividualCustomerLoanTypeResponse> getByCustomerIdPaginated(Long customerId, Pageable pageable);
    List<GetIndividualCustomerLoanTypeResponse> getByMinCreditScore(Integer creditScore);
    PageDto<GetIndividualCustomerLoanTypeResponse> getByMinCreditScorePaginated(Integer creditScore, Pageable pageable);
    void delete(Long id);
} 