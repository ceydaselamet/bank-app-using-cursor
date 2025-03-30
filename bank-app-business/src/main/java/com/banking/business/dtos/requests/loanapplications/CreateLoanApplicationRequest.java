package com.banking.business.dtos.requests.loanapplications;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanApplicationRequest {
    @NotNull(message = "Loan type ID is required")
    private Long loanTypeId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be at least 1")
    private Integer term;
} 