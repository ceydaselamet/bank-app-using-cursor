package com.banking.business.dtos.requests.loantypes;

import com.banking.enums.CustomerType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanTypeRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Customer type is required")
    private CustomerType customerType;

    private Long parentLoanTypeId;

    @NotNull(message = "Minimum amount is required")
    @DecimalMin(value = "0.0", message = "Minimum amount must be greater than or equal to 0")
    private BigDecimal minAmount;

    @NotNull(message = "Maximum amount is required")
    @DecimalMin(value = "0.0", message = "Maximum amount must be greater than or equal to 0")
    private BigDecimal maxAmount;

    @NotNull(message = "Minimum term is required")
    @Min(value = 1, message = "Minimum term must be at least 1")
    private Integer minTerm;

    @NotNull(message = "Maximum term is required")
    @Min(value = 1, message = "Maximum term must be at least 1")
    private Integer maxTerm;

    @NotNull(message = "Base interest rate is required")
    @DecimalMin(value = "0.0", message = "Base interest rate must be greater than or equal to 0")
    private BigDecimal baseInterestRate;
} 