package com.banking.entities;

import com.banking.enums.LoanApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.banking.core.entities.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "individual_customer_id")
    private IndividualCustomer individualCustomer;

    @ManyToOne
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer corporateCustomer;

    @ManyToOne
    @JoinColumn(name = "individual_loan_type_id")
    private IndividualCustomerLoanType individualCustomerLoanType;

    @ManyToOne
    @JoinColumn(name = "corporate_loan_type_id")
    private CorporateCustomerLoanType corporateCustomerLoanType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private BigDecimal monthlyPayment;

    @Column(nullable = false)
    private BigDecimal totalPayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    private LocalDateTime decisionDate;

    private String rejectionReason;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        applicationDate = now;
    }
} 