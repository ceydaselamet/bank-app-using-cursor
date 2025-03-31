package com.banking.webapi.controllers;

import com.banking.business.abstracts.CorporateCustomerLoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateCorporateCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetCorporateCustomerLoanTypeResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.core.utils.paging.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/corporate-loan-types")
@AllArgsConstructor
@Tag(name = "Corporate Customer Loan Types", description = "Corporate Customer Loan Type Management APIs")
public class CorporateCustomerLoanTypeController {
    private final CorporateCustomerLoanTypeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new corporate customer loan type")
    public GetCorporateCustomerLoanTypeResponse create(@Valid @RequestBody CreateCorporateCustomerLoanTypeRequest request) {
        return service.create(request);
    }

    @GetMapping
    @Operation(summary = "Get all corporate customer loan types")
    public List<GetCorporateCustomerLoanTypeResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all corporate customer loan types with pagination")
    public PageDto<GetCorporateCustomerLoanTypeResponse> getAllPaginated(@Valid PageRequest pageRequest) {
        return service.getAllPaginated(pageRequest.toPageable());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a corporate customer loan type by ID")
    public GetCorporateCustomerLoanTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-customer/{customerId}")
    @Operation(summary = "Get loan types by customer ID")
    public List<GetCorporateCustomerLoanTypeResponse> getByCustomerId(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/by-customer/{customerId}/paginated")
    @Operation(summary = "Get paginated loan types by customer ID")
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByCustomerIdPaginated(
            @PathVariable Long customerId,
            @Valid PageRequest pageRequest) {
        return service.getByCustomerIdPaginated(customerId, pageRequest.toPageable());
    }

    @GetMapping("/by-annual-revenue/{annualRevenue}")
    @Operation(summary = "Get loan types by minimum annual revenue")
    public List<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenue(@PathVariable BigDecimal annualRevenue) {
        return service.getByMinAnnualRevenue(annualRevenue);
    }

    @GetMapping("/by-annual-revenue/{annualRevenue}/paginated")
    @Operation(summary = "Get paginated loan types by minimum annual revenue")
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenuePaginated(
            @PathVariable BigDecimal annualRevenue,
            @Valid PageRequest pageRequest) {
        return service.getByMinAnnualRevenuePaginated(annualRevenue, pageRequest.toPageable());
    }

    @GetMapping("/by-years-in-business/{yearsInBusiness}")
    @Operation(summary = "Get loan types by minimum years in business")
    public List<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusiness(@PathVariable Integer yearsInBusiness) {
        return service.getByMinYearsInBusiness(yearsInBusiness);
    }

    @GetMapping("/by-years-in-business/{yearsInBusiness}/paginated")
    @Operation(summary = "Get paginated loan types by minimum years in business")
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusinessPaginated(
            @PathVariable Integer yearsInBusiness,
            @Valid PageRequest pageRequest) {
        return service.getByMinYearsInBusinessPaginated(yearsInBusiness, pageRequest.toPageable());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a corporate customer loan type")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 