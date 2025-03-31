package com.banking.business.abstracts;

import com.banking.business.dtos.requests.individual.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.individual.IndividualCustomerResponse;
import com.banking.core.utils.paging.PageDto;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IndividualCustomerService {
    IndividualCustomerResponse add(CreateIndividualCustomerRequest request);
    IndividualCustomerResponse getById(Long id);
    IndividualCustomerResponse getByNationalId(String nationalId);
    List<IndividualCustomerResponse> getAll();
    List<IndividualCustomerResponse> getAllByMinCreditScore(Integer minScore);
    List<IndividualCustomerResponse> getAllByIncomeRange(Double minIncome, Double maxIncome);
    void delete(Long id);
    PageDto<IndividualCustomerResponse> getAllPaginated(Pageable pageable);
} 