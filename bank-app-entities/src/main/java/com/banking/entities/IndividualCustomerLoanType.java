package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "individual_customer_loan_types")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class IndividualCustomerLoanType extends LoanType {
    @Column(nullable = false)
    private Integer minCreditScore;

    @Column(nullable = false)
    private BigDecimal maxDebtToIncomeRatio;

    @Column(nullable = false)
    private Boolean requiresCollateral;
    
    @ManyToOne
    @JoinColumn(name = "individual_customer_id")
    private IndividualCustomer customer;
} 