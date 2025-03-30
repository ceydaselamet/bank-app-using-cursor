package com.banking.business.dtos.responses.individual;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndividualCustomerResponse {
    private Long id;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private Double monthlyIncome;
    private Integer creditScore;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String city;
} 