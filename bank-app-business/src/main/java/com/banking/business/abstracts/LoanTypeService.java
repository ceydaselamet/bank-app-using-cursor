package com.banking.business.abstracts;

import com.banking.business.dtos.requests.loantypes.CreateLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetLoanTypeResponse;
import com.banking.enums.CustomerType;

import java.util.List;

public interface LoanTypeService {
    GetLoanTypeResponse create(CreateLoanTypeRequest request);
    List<GetLoanTypeResponse> getAll();
    List<GetLoanTypeResponse> getAllByCustomerType(CustomerType customerType);
    List<GetLoanTypeResponse> getAllMainTypes();
    List<GetLoanTypeResponse> getAllSubTypes(Long parentId);
    GetLoanTypeResponse getById(Long id);
    void delete(Long id);
} 