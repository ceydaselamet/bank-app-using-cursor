package com.banking.webapi.controllers;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.dtos.requests.corporate.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.corporate.CorporateCustomerResponse;
import com.banking.core.utils.paging.PageDto;
import com.banking.core.utils.paging.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corporate-customers")
@RequiredArgsConstructor
@Tag(name = "Corporate Customers", description = "Corporate Customer Management APIs")
public class CorporateCustomersController {

    private final CorporateCustomerService corporateCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new corporate customer")
    public CorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest request) {
        return corporateCustomerService.add(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get corporate customer by ID")
    public CorporateCustomerResponse getById(@PathVariable Long id) {
        return corporateCustomerService.getById(id);
    }

    @GetMapping("/by-tax-number/{taxNumber}")
    @Operation(summary = "Get corporate customer by tax number")
    public CorporateCustomerResponse getByTaxNumber(@PathVariable String taxNumber) {
        return corporateCustomerService.getByTaxNumber(taxNumber);
    }

    @GetMapping
    @Operation(summary = "Get all corporate customers")
    public List<CorporateCustomerResponse> getAll() {
        return corporateCustomerService.getAll();
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all corporate customers with pagination")
    public PageDto<CorporateCustomerResponse> getAllPaginated(@Valid PageRequest pageRequest) {
        return PageDto.of(corporateCustomerService.getAllPaginated(pageRequest.toPageable()));
    }

    @GetMapping("/by-tax-office/{taxOffice}")
    @Operation(summary = "Get corporate customers by tax office")
    public List<CorporateCustomerResponse> getAllByTaxOffice(@PathVariable String taxOffice) {
        return corporateCustomerService.getAllByTaxOffice(taxOffice);
    }

    @GetMapping("/by-min-annual-revenue/{minRevenue}")
    @Operation(summary = "Get corporate customers by minimum annual revenue")
    public List<CorporateCustomerResponse> getAllByMinAnnualRevenue(@PathVariable Double minRevenue) {
        return corporateCustomerService.getAllByMinAnnualRevenue(minRevenue);
    }

    @GetMapping("/by-size-and-rating")
    @Operation(summary = "Get corporate customers by company size and credit rating")
    public List<CorporateCustomerResponse> getAllByCompanySizeAndCreditRating(
            @RequestParam Integer minSize,
            @RequestParam String rating) {
        return corporateCustomerService.getAllByCompanySizeAndCreditRating(minSize, rating);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete corporate customer by ID")
    public void delete(@PathVariable Long id) {
        corporateCustomerService.delete(id);
    }
} 