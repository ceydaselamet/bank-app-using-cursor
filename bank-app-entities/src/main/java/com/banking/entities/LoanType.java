package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import com.banking.core.entities.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_types")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class LoanType extends BaseEntity<Long> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal minAmount;

    @Column(nullable = false)
    private BigDecimal maxAmount;

    @Column(nullable = false)
    private Integer minTerm;

    @Column(nullable = false)
    private Integer maxTerm;

    @Column(nullable = false)
    private BigDecimal interestRate;
} 