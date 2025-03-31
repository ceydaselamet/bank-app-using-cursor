package com.banking.webapi.controllers;

import com.banking.business.abstracts.IndividualCustomerLoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateIndividualCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetIndividualCustomerLoanTypeResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.core.utils.paging.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individual-loan-types")
@AllArgsConstructor
@Tag(name = "Individual Customer Loan Types", description = "Individual Customer Loan Type Management APIs")
public class IndividualCustomerLoanTypeController {
    private final IndividualCustomerLoanTypeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new individual customer loan type")
    public GetIndividualCustomerLoanTypeResponse create(@Valid @RequestBody CreateIndividualCustomerLoanTypeRequest request) {
        return service.create(request);
    }

    @GetMapping
    @Operation(summary = "Get all individual customer loan types")
    public List<GetIndividualCustomerLoanTypeResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all individual customer loan types with pagination")
    public PageDto<GetIndividualCustomerLoanTypeResponse> getAllPaginated(@Valid PageRequest pageRequest) {
        return service.getAllPaginated(pageRequest.toPageable());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an individual customer loan type by ID")
    public GetIndividualCustomerLoanTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-customer/{customerId}")
    @Operation(summary = "Get loan types by customer ID")
    public List<GetIndividualCustomerLoanTypeResponse> getByCustomerId(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/by-customer/{customerId}/paginated")
    @Operation(summary = "Get paginated loan types by customer ID")
    public PageDto<GetIndividualCustomerLoanTypeResponse> getByCustomerIdPaginated(
            @PathVariable Long customerId,
            @Valid PageRequest pageRequest) {
        return service.getByCustomerIdPaginated(customerId, pageRequest.toPageable());
    }

    @GetMapping("/by-credit-score/{creditScore}")
    @Operation(summary = "Get loan types by minimum credit score")
    public List<GetIndividualCustomerLoanTypeResponse> getByMinCreditScore(@PathVariable Integer creditScore) {
        return service.getByMinCreditScore(creditScore);
    }

    @GetMapping("/by-credit-score/{creditScore}/paginated")
    @Operation(summary = "Get paginated loan types by minimum credit score")
    public PageDto<GetIndividualCustomerLoanTypeResponse> getByMinCreditScorePaginated(
            @PathVariable Integer creditScore,
            @Valid PageRequest pageRequest) {
        return service.getByMinCreditScorePaginated(creditScore, pageRequest.toPageable());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an individual customer loan type")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 