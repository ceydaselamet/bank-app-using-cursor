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
public class CreateIndividualCustomerLoanTypeRequest {
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

    @NotNull(message = "Interest rate is required")
    @Positive(message = "Interest rate must be positive")
    private BigDecimal baseInterestRate;

    @NotNull(message = "Minimum credit score is required")
    @Positive(message = "Minimum credit score must be positive")
    private Integer minCreditScore;

    @NotNull(message = "Maximum debt to income ratio is required")
    @Positive(message = "Maximum debt to income ratio must be positive")
    private BigDecimal maxDebtToIncomeRatio;

    private boolean requiresCollateral;
} 