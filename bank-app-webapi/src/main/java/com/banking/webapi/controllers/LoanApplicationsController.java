package com.banking.webapi.controllers;

import com.banking.business.abstracts.LoanApplicationService;
import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.enums.LoanApplicationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-applications")
@AllArgsConstructor
@Tag(name = "Loan Applications", description = "Loan Application Management APIs")
public class LoanApplicationsController {
    private final LoanApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new loan application")
    public GetLoanApplicationResponse create(@Valid @RequestBody CreateLoanApplicationRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loan application by ID")
    public GetLoanApplicationResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-customer")
    @Operation(summary = "Get loan applications by customer ID and status")
    public List<GetLoanApplicationResponse> getByCustomerIdAndStatus(
            @RequestParam Long customerId,
            @RequestParam(required = false) LoanApplicationStatus status) {
        return service.getByCustomerIdAndStatus(customerId, status);
    }

    @GetMapping("/by-customer/paginated")
    @Operation(summary = "Get paginated loan applications by customer ID")
    public PageDto<GetLoanApplicationResponse> getByCustomerId(
            @RequestParam Long customerId,
            @Parameter(hidden = true) Pageable pageable) {
        return service.getByCustomerId(customerId, pageable);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve loan application")
    public GetLoanApplicationResponse approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject loan application")
    public GetLoanApplicationResponse reject(
            @PathVariable Long id,
            @RequestParam String reason) {
        return service.reject(id, reason);
    }
} 