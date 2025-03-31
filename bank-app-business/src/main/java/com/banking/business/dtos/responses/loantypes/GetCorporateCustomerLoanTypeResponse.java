package com.banking.business.dtos.responses.loantypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCorporateCustomerLoanTypeResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private BigDecimal baseInterestRate;
    private Integer minYearsInBusiness;
    private BigDecimal minAnnualRevenue;
    private Boolean requiresBusinessPlan;
    private Boolean requiresFinancialStatements;
} 