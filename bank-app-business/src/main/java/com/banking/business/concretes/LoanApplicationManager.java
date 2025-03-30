package com.banking.business.concretes;

import com.banking.business.abstracts.LoanApplicationService;
import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.business.mappings.LoanApplicationMapper;
import com.banking.business.rules.LoanApplicationBusinessRules;
import com.banking.core.utils.paging.PageDto;
import com.banking.entities.LoanApplication;
import com.banking.enums.CustomerType;
import com.banking.enums.LoanApplicationStatus;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import com.banking.repositories.abstracts.LoanApplicationRepository;
import com.banking.repositories.abstracts.LoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanApplicationManager implements LoanApplicationService {
    private final LoanApplicationRepository repository;
    private final LoanTypeRepository loanTypeRepository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final LoanApplicationBusinessRules rules;
    private final LoanApplicationMapper mapper;

    @Override
    @Transactional
    public GetLoanApplicationResponse create(CreateLoanApplicationRequest request) {
        var loanType = loanTypeRepository.findById(request.getLoanTypeId()).orElseThrow();
        var application = mapper.createLoanApplicationRequestToLoanApplication(request);

        // Set loan type and validate amount and term
        application.setLoanType(loanType);
        rules.validateLoanAmount(loanType, request.getAmount());
        rules.validateLoanTerm(loanType, request.getTerm());

        // Set customer based on loan type
        if (loanType.getCustomerType() == CustomerType.INDIVIDUAL) {
            var customer = individualCustomerRepository.findById(request.getCustomerId()).orElseThrow();
            application.setIndividualCustomer(customer);
            rules.validateCustomerType(loanType, CustomerType.INDIVIDUAL);
        } else {
            var customer = corporateCustomerRepository.findById(request.getCustomerId()).orElseThrow();
            application.setCorporateCustomer(customer);
            rules.validateCustomerType(loanType, CustomerType.CORPORATE);
        }

        // Calculate loan details
        application.setStatus(LoanApplicationStatus.PENDING);
        application.setInterestRate(loanType.getBaseInterestRate());
        calculatePayments(application);

        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }

    @Override
    public GetLoanApplicationResponse getById(Long id) {
        rules.checkIfLoanApplicationExists(id);
        return mapper.loanApplicationToGetLoanApplicationResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public List<GetLoanApplicationResponse> getByCustomerIdAndStatus(Long customerId, LoanApplicationStatus status) {
        return repository.findByIndividualCustomer_IdAndStatus(customerId, status).stream()
                .map(mapper::loanApplicationToGetLoanApplicationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<GetLoanApplicationResponse> getByCustomerId(Long customerId, Pageable pageable) {
        var page = repository.findByIndividualCustomer_Id(customerId, pageable);
        return PageDto.of(page.map(mapper::loanApplicationToGetLoanApplicationResponse));
    }

    @Override
    @Transactional
    public GetLoanApplicationResponse approve(Long id) {
        var application = repository.findById(id).orElseThrow();
        rules.checkIfCanBeApprovedOrRejected(application);

        application.setStatus(LoanApplicationStatus.APPROVED);
        application.setDecisionDate(LocalDateTime.now());

        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }

    @Override
    @Transactional
    public GetLoanApplicationResponse reject(Long id, String reason) {
        var application = repository.findById(id).orElseThrow();
        rules.checkIfCanBeApprovedOrRejected(application);

        application.setStatus(LoanApplicationStatus.REJECTED);
        application.setRejectionReason(reason);
        application.setDecisionDate(LocalDateTime.now());

        return mapper.loanApplicationToGetLoanApplicationResponse(repository.save(application));
    }

    private void calculatePayments(LoanApplication application) {
        var amount = application.getAmount();
        var term = application.getTerm();
        var monthlyInterestRate = application.getInterestRate().divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);
        
        // Monthly Payment = P * r * (1 + r)^n / ((1 + r)^n - 1)
        // where P = principal, r = monthly interest rate, n = number of months
        var temp = BigDecimal.ONE.add(monthlyInterestRate).pow(term);
        var monthlyPayment = amount.multiply(monthlyInterestRate).multiply(temp)
                .divide(temp.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        
        application.setMonthlyPayment(monthlyPayment);
        application.setTotalPayment(monthlyPayment.multiply(BigDecimal.valueOf(term)));
    }
} 