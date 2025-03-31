package com.banking.business.concretes;

import com.banking.business.abstracts.LoanApplicationService;
import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.business.mappings.LoanApplicationMapper;
import com.banking.business.rules.LoanApplicationBusinessRules;
import com.banking.core.utils.paging.PageDto;
import com.banking.entities.*;
import com.banking.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanApplicationManager implements LoanApplicationService {
    private final LoanApplicationRepository repository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final IndividualCustomerLoanTypeRepository individualLoanTypeRepository;
    private final CorporateCustomerLoanTypeRepository corporateLoanTypeRepository;
    private final LoanApplicationMapper mapper;
    private final LoanApplicationBusinessRules rules;

    @Override
    @Transactional
    public GetLoanApplicationResponse create(CreateLoanApplicationRequest request) {
        var application = mapper.createLoanApplicationRequestToLoanApplication(request);
        
        // Set customer and loan type based on customer type
        var individualCustomer = individualCustomerRepository.findById(request.getCustomerId()).orElse(null);
        if (individualCustomer != null) {
            application.setIndividualCustomer(individualCustomer);
            var loanType = individualLoanTypeRepository.findById(request.getLoanTypeId())
                .orElseThrow(() -> new RuntimeException("Loan type not found"));
            application.setIndividualCustomerLoanType(loanType);
            
            // Validate eligibility
            calculateLoanDetails(application, loanType);
            rules.validateIndividualCustomerEligibility(individualCustomer, loanType, application.getMonthlyPayment());
        } else {
            var corporateCustomer = corporateCustomerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
            application.setCorporateCustomer(corporateCustomer);
            var loanType = corporateLoanTypeRepository.findById(request.getLoanTypeId())
                .orElseThrow(() -> new RuntimeException("Loan type not found"));
            application.setCorporateCustomerLoanType(loanType);
            
            // Validate eligibility
            calculateLoanDetails(application, loanType);
            rules.validateCorporateCustomerEligibility(corporateCustomer, loanType);
        }

        application.setStatus(LoanApplicationStatus.PENDING);
        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }

    private void calculateLoanDetails(LoanApplication application, IndividualCustomerLoanType loanType) {
        rules.validateLoanAmount(loanType, application.getAmount());
        rules.validateLoanTerm(loanType, application.getTerm());
        
        application.setInterestRate(loanType.getInterestRate());
        calculatePayments(application);
    }

    private void calculateLoanDetails(LoanApplication application, CorporateCustomerLoanType loanType) {
        rules.validateLoanAmount(loanType, application.getAmount());
        rules.validateLoanTerm(loanType, application.getTerm());
        
        application.setInterestRate(loanType.getInterestRate());
        calculatePayments(application);
    }

    private void calculatePayments(LoanApplication application) {
        var amount = application.getAmount();
        var term = application.getTerm();
        var monthlyInterestRate = application.getInterestRate().divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);
        
        // Monthly Payment = P * r * (1 + r)^n / ((1 + r)^n - 1)
        // Where: P = Principal, r = Monthly Interest Rate, n = Number of Payments
        var temp = BigDecimal.ONE.add(monthlyInterestRate).pow(term);
        var monthlyPayment = amount.multiply(monthlyInterestRate).multiply(temp)
            .divide(temp.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        
        application.setMonthlyPayment(monthlyPayment);
        application.setTotalPayment(monthlyPayment.multiply(BigDecimal.valueOf(term)).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public GetLoanApplicationResponse getById(Long id) {
        rules.checkIfLoanApplicationExists(id);
        return mapper.loanApplicationToGetLoanApplicationResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public List<GetLoanApplicationResponse> getByCustomerIdAndStatus(Long customerId, LoanApplicationStatus status) {
        var applications = status != null ? 
            repository.findByCustomerIdAndStatus(customerId, status) :
            repository.findByCustomerId(customerId);
            
        return applications.stream()
            .map(mapper::loanApplicationToGetLoanApplicationResponse)
            .toList();
    }

    @Override
    public PageDto<GetLoanApplicationResponse> getByCustomerId(Long customerId, Pageable pageable) {
        Page<GetLoanApplicationResponse> page = repository.findByCustomerId(customerId, pageable)
            .map(mapper::loanApplicationToGetLoanApplicationResponse);
        return PageDto.of(page);
    }

    @Override
    @Transactional
    public GetLoanApplicationResponse approve(Long id) {
        rules.checkIfLoanApplicationExists(id);
        var application = repository.findById(id).orElseThrow();
        rules.checkIfCanBeApprovedOrRejected(application);
        
        application.setStatus(LoanApplicationStatus.APPROVED);
        application.setDecisionDate(LocalDateTime.now());
        
        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }

    @Override
    @Transactional
    public GetLoanApplicationResponse reject(Long id, String reason) {
        rules.checkIfLoanApplicationExists(id);
        var application = repository.findById(id).orElseThrow();
        rules.checkIfCanBeApprovedOrRejected(application);
        
        application.setStatus(LoanApplicationStatus.REJECTED);
        application.setRejectionReason(reason);
        application.setDecisionDate(LocalDateTime.now());
        
        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }
} 