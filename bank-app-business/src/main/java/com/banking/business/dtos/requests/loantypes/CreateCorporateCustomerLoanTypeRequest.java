package com.banking.business.dtos.requests.loantypes;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerLoanTypeRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Minimum amount is required")
    @Positive(message = "Minimum amount must be positive")
    private BigDecimal minAmount;

    @NotNull(message = "Maximum amount is required")
    @Positive(message = "Maximum amount must be positive")
    private BigDecimal maxAmount;

    @NotNull(message = "Minimum term is required")
    @Positive(message = "Minimum term must be positive")
    private Integer minTerm;

    @NotNull(message = "Maximum term is required")
    @Positive(message = "Maximum term must be positive")
    private Integer maxTerm;

    @NotNull(message = "Base interest rate is required")
    @PositiveOrZero(message = "Base interest rate must be positive or zero")
    private BigDecimal baseInterestRate;

    @NotNull(message = "Minimum years in business is required")
    @Positive(message = "Minimum years in business must be positive")
    private Integer minYearsInBusiness;

    @NotNull(message = "Minimum annual revenue is required")
    @Positive(message = "Minimum annual revenue must be positive")
    private BigDecimal minAnnualRevenue;

    @NotNull(message = "Requires business plan field is required")
    private Boolean requiresBusinessPlan;

    @NotNull(message = "Requires financial statements field is required")
    private Boolean requiresFinancialStatements;
} 