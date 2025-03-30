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
    private Long loanTypeId;
    private String loanTypeName;
    private Long customerId;
    private String customerName;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal interestRate;
    private LoanApplicationStatus status;
    private String rejectionReason;
    private BigDecimal monthlyPayment;
    private BigDecimal totalPayment;
    private LocalDateTime applicationDate;
    private LocalDateTime decisionDate;
} 