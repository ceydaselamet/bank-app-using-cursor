package com.banking.business.abstracts;

import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.enums.LoanApplicationStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanApplicationService {
    GetLoanApplicationResponse create(CreateLoanApplicationRequest request);
    GetLoanApplicationResponse getById(Long id);
    List<GetLoanApplicationResponse> getByCustomerIdAndStatus(Long customerId, LoanApplicationStatus status);
    PageDto<GetLoanApplicationResponse> getByCustomerId(Long customerId, Pageable pageable);
    GetLoanApplicationResponse approve(Long id);
    GetLoanApplicationResponse reject(Long id, String reason);
} 