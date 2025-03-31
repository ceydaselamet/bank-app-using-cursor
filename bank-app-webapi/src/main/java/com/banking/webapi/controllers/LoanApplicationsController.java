package com.banking.webapi.controllers;

import com.banking.business.abstracts.LoanApplicationService;
import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.core.utils.paging.PageRequest;
import com.banking.enums.LoanApplicationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-applications")
@AllArgsConstructor
@Tag(name = "Loan Applications", description = "Loan Application Management APIs")
public class LoanApplicationsController {
    private final LoanApplicationService loanApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new loan application")
    public GetLoanApplicationResponse create(@Valid @RequestBody CreateLoanApplicationRequest request) {
        return loanApplicationService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a loan application by ID")
    public GetLoanApplicationResponse getById(@PathVariable Long id) {
        return loanApplicationService.getById(id);
    }

    @GetMapping("/by-customer")
    @Operation(summary = "Get loan applications by customer ID and optional status")
    public List<GetLoanApplicationResponse> getByCustomerIdAndStatus(
            @RequestParam Long customerId,
            @Parameter(description = "Filter by application status (optional)") 
            @RequestParam(required = false) LoanApplicationStatus status) {
        return loanApplicationService.getByCustomerIdAndStatus(customerId, status);
    }

    @GetMapping("/by-customer/paginated")
    @Operation(summary = "Get paginated loan applications by customer ID")
    public PageDto<GetLoanApplicationResponse> getByCustomerId(
            @RequestParam Long customerId,
            @Valid PageRequest pageRequest) {
        return loanApplicationService.getByCustomerId(customerId, pageRequest.toPageable());
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve a loan application")
    public GetLoanApplicationResponse approve(@PathVariable Long id) {
        return loanApplicationService.approve(id);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject a loan application")
    public GetLoanApplicationResponse reject(
            @PathVariable Long id,
            @RequestParam String reason) {
        return loanApplicationService.reject(id, reason);
    }
} 