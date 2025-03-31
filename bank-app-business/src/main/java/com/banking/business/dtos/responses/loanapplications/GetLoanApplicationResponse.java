package com.banking.business.dtos.responses.loanapplications;

import com.banking.enums.LoanApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanApplicationResponse {
    private Long id;
    private Long customerId;
    private String customerName;
    private String customerNumber;
    private Long loanTypeId;
    private String loanTypeName;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal interestRate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalPayment;
    private LoanApplicationStatus status;
    private LocalDateTime decisionDate;
    private String rejectionReason;
    private LocalDateTime createdAt;
} 