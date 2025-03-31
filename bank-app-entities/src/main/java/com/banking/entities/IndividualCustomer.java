package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "individual_customers")
@Getter
@Setter
@DiscriminatorValue("INDIVIDUAL")
public class IndividualCustomer extends Customer {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "national_id", nullable = false, unique = true, length = 11)
    private String nationalId;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    @Column(name = "current_debt")
    private BigDecimal currentDebt;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "city")
    private String city;
} 