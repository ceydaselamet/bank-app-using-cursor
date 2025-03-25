package com.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "national_id", nullable = false, unique = true)
    private String nationalId;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "monthly_income")
    private Double monthlyIncome;

    @Column(name = "credit_score")
    private Integer creditScore;
} 