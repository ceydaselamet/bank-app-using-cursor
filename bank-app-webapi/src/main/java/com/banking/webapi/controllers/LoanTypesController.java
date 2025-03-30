package com.banking.webapi.controllers;

import com.banking.business.abstracts.LoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetLoanTypeResponse;
import com.banking.enums.CustomerType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-types")
@AllArgsConstructor
@Tag(name = "Loan Types", description = "Loan Type Management APIs")
public class LoanTypesController {
    private final LoanTypeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new loan type")
    public GetLoanTypeResponse create(@Valid @RequestBody CreateLoanTypeRequest request) {
        return service.create(request);
    }

    @GetMapping
    @Operation(summary = "Get all loan types")
    public List<GetLoanTypeResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-customer-type/{customerType}")
    @Operation(summary = "Get loan types by customer type")
    public List<GetLoanTypeResponse> getAllByCustomerType(@PathVariable CustomerType customerType) {
        return service.getAllByCustomerType(customerType);
    }

    @GetMapping("/main-types")
    @Operation(summary = "Get all main loan types")
    public List<GetLoanTypeResponse> getAllMainTypes() {
        return service.getAllMainTypes();
    }

    @GetMapping("/sub-types/{parentId}")
    @Operation(summary = "Get all sub loan types by parent ID")
    public List<GetLoanTypeResponse> getAllSubTypes(@PathVariable Long parentId) {
        return service.getAllSubTypes(parentId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loan type by ID")
    public GetLoanTypeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete loan type by ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
} 