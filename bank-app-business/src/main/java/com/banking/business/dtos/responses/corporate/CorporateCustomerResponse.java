package com.banking.business.dtos.responses.corporate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateCustomerResponse {
    private Long id;
    private String customerNumber;
    private String companyName;
    private String taxNumber;
    private String taxOffice;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate establishmentDate;
    private Double annualRevenue;
    private Integer companySize;
    private String creditRating;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
} 