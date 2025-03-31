package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "corporate_customer_loan_types")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CorporateCustomerLoanType extends LoanType {
    @Column(nullable = false)
    private Integer minYearsInBusiness;

    @Column(nullable = false)
    private BigDecimal minAnnualRevenue;

    @Column(nullable = false)
    private Boolean requiresBusinessPlan;
    
    @Column(nullable = false)
    private Boolean requiresFinancialStatements;
    
    @ManyToOne
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer customer;
} 