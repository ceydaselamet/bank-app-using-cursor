package com.banking.webapi.controllers;

import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.dtos.requests.individual.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.individual.IndividualCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/individual-customers")
@RequiredArgsConstructor
@Tag(name = "Individual Customers", description = "Individual Customer Management APIs")
public class IndividualCustomersController {

    private final IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new individual customer")
    public IndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest request) {
        return individualCustomerService.add(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get individual customer by ID")
    public IndividualCustomerResponse getById(@PathVariable Long id) {
        return individualCustomerService.getById(id);
    }

    @GetMapping("/by-national-id/{nationalId}")
    @Operation(summary = "Get individual customer by national ID")
    public IndividualCustomerResponse getByNationalId(@PathVariable String nationalId) {
        return individualCustomerService.getByNationalId(nationalId);
    }

    @GetMapping
    @Operation(summary = "Get all individual customers")
    public List<IndividualCustomerResponse> getAll() {
        return individualCustomerService.getAll();
    }

    @GetMapping("/by-min-credit-score/{minScore}")
    @Operation(summary = "Get individual customers by minimum credit score")
    public List<IndividualCustomerResponse> getAllByMinCreditScore(@PathVariable Integer minScore) {
        return individualCustomerService.getAllByMinCreditScore(minScore);
    }

    @GetMapping("/by-income-range")
    @Operation(summary = "Get individual customers by income range")
    public List<IndividualCustomerResponse> getAllByIncomeRange(
            @RequestParam Double minIncome,
            @RequestParam Double maxIncome) {
        return individualCustomerService.getAllByIncomeRange(minIncome, maxIncome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete individual customer by ID")
    public void delete(@PathVariable Long id) {
        individualCustomerService.delete(id);
    }
} 