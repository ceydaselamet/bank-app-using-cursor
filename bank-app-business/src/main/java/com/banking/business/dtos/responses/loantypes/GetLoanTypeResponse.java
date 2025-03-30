package com.banking.business.dtos.responses.loantypes;

import com.banking.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanTypeResponse {
    private Long id;
    private String name;
    private String description;
    private CustomerType customerType;
    private Long parentLoanTypeId;
    private String parentLoanTypeName;
    private List<GetLoanTypeResponse> subTypes;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private BigDecimal baseInterestRate;
} 