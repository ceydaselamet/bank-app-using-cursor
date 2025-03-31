package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "corporate_customers")
@Getter
@Setter
@DiscriminatorValue("CORPORATE")
public class CorporateCustomer extends Customer {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "tax_number", nullable = false, unique = true)
    private String taxNumber;

    @Column(name = "tax_office")
    private String taxOffice;

    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @Column(name = "annual_revenue", nullable = false)
    private BigDecimal annualRevenue;

    @Column(name = "company_size")
    private Integer companySize;

    @Column(name = "credit_rating")
    private String creditRating;
} 