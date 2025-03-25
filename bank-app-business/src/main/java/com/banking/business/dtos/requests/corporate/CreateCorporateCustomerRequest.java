package com.banking.business.dtos.requests.corporate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
    
    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @NotBlank(message = "Tax number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Tax number must be 10 digits")
    private String taxNumber;

    @NotBlank(message = "Tax office is required")
    private String taxOffice;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "Establishment date is required")
    @Past(message = "Establishment date must be in the past")
    private LocalDate establishmentDate;

    @NotNull(message = "Annual revenue is required")
    @Positive(message = "Annual revenue must be positive")
    private Double annualRevenue;

    @NotNull(message = "Company size is required")
    @Positive(message = "Company size must be positive")
    private Integer companySize;

    private String address;
} 