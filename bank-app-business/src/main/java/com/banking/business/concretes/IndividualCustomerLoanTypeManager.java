package com.banking.business.concretes;

import com.banking.business.abstracts.IndividualCustomerLoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateIndividualCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetIndividualCustomerLoanTypeResponse;
import com.banking.business.mappings.IndividualCustomerLoanTypeMapper;
import com.banking.business.rules.IndividualCustomerLoanTypeBusinessRules;
import com.banking.core.utils.paging.PageDto;
import com.banking.repositories.abstracts.IndividualCustomerLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerLoanTypeManager implements IndividualCustomerLoanTypeService {
    private final IndividualCustomerLoanTypeRepository repository;
    private final IndividualCustomerLoanTypeMapper mapper;
    private final IndividualCustomerLoanTypeBusinessRules rules;

    @Override
    @Transactional
    public GetIndividualCustomerLoanTypeResponse create(CreateIndividualCustomerLoanTypeRequest request) {
        rules.validateLoanAmount(request.getMinAmount(), request.getMaxAmount());
        rules.validateTermRange(request.getMinTerm(), request.getMaxTerm());
        rules.validateCreditScore(request.getMinCreditScore());
        rules.validateDebtToIncomeRatio(request.getMaxDebtToIncomeRatio());
        
        var loanType = mapper.createRequestToIndividualCustomerLoanType(request);
        
        // Explicitly set ID to null to ensure a new record is created
        loanType.setId(null);
        
        return mapper.individualCustomerLoanTypeToGetResponse(repository.save(loanType));
    }

    @Override
    public GetIndividualCustomerLoanTypeResponse getById(Long id) {
        rules.checkIfExists(id);
        return mapper.individualCustomerLoanTypeToGetResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public List<GetIndividualCustomerLoanTypeResponse> getAll() {
        return repository.findAll().stream()
            .map(mapper::individualCustomerLoanTypeToGetResponse)
            .toList();
    }

    @Override
    public PageDto<GetIndividualCustomerLoanTypeResponse> getAllPaginated(Pageable pageable) {
        Page<GetIndividualCustomerLoanTypeResponse> page = repository.findAll(pageable)
            .map(mapper::individualCustomerLoanTypeToGetResponse);
        return PageDto.of(page);
    }

    @Override
    public List<GetIndividualCustomerLoanTypeResponse> getByMinCreditScore(Integer creditScore) {
        return repository.findByMinCreditScoreLessThanEqual(creditScore).stream()
            .map(mapper::individualCustomerLoanTypeToGetResponse)
            .toList();
    }

    @Override
    public List<GetIndividualCustomerLoanTypeResponse> getByCustomerId(Long customerId) {
        return repository.findByCustomer_Id(customerId).stream()
            .map(mapper::individualCustomerLoanTypeToGetResponse)
            .toList();
    }

    @Override
    public PageDto<GetIndividualCustomerLoanTypeResponse> getByCustomerIdPaginated(Long customerId, Pageable pageable) {
        Page<GetIndividualCustomerLoanTypeResponse> page = repository.findByCustomer_Id(customerId, pageable)
            .map(mapper::individualCustomerLoanTypeToGetResponse);
        return PageDto.of(page);
    }

    @Override
    public PageDto<GetIndividualCustomerLoanTypeResponse> getByMinCreditScorePaginated(Integer creditScore, Pageable pageable) {
        Page<GetIndividualCustomerLoanTypeResponse> page = repository.findByMinCreditScoreLessThanEqual(creditScore, pageable)
            .map(mapper::individualCustomerLoanTypeToGetResponse);
        return PageDto.of(page);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfExists(id);
        repository.deleteById(id);
    }
} 