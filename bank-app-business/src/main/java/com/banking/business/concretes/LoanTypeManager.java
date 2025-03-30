package com.banking.business.concretes;

import com.banking.business.abstracts.LoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetLoanTypeResponse;
import com.banking.business.mappings.LoanTypeMapper;
import com.banking.business.rules.LoanTypeBusinessRules;
import com.banking.entities.LoanType;
import com.banking.enums.CustomerType;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import com.banking.repositories.abstracts.LoanApplicationRepository;
import com.banking.repositories.abstracts.LoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanTypeManager implements LoanTypeService {
    private final LoanTypeRepository repository;
    private final LoanTypeBusinessRules rules;
    private final LoanTypeMapper mapper;

    @Override
    public GetLoanTypeResponse create(CreateLoanTypeRequest request) {
        rules.validateAmountRange(request.getMinAmount(), request.getMaxAmount());
        rules.validateTermRange(request.getMinTerm(), request.getMaxTerm());
        rules.checkIfParentLoanTypeExists(request.getParentLoanTypeId());

        var loanType = mapper.createLoanTypeRequestToLoanType(request);
        if (request.getParentLoanTypeId() != null) {
            var parentLoanType = repository.findById(request.getParentLoanTypeId()).orElseThrow();
            loanType.setParentLoanType(parentLoanType);
        }

        return mapper.loanTypeToGetLoanTypeResponse(repository.save(loanType));
    }

    @Override
    public List<GetLoanTypeResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::loanTypeToGetLoanTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetLoanTypeResponse> getAllByCustomerType(CustomerType customerType) {
        return repository.findByCustomerType(customerType).stream()
                .map(mapper::loanTypeToGetLoanTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetLoanTypeResponse> getAllMainTypes() {
        return repository.findByParentLoanTypeIsNull().stream()
                .map(mapper::loanTypeToGetLoanTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetLoanTypeResponse> getAllSubTypes(Long parentId) {
        rules.checkIfLoanTypeExists(parentId);
        return repository.findByParentLoanType_Id(parentId).stream()
                .map(mapper::loanTypeToGetLoanTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetLoanTypeResponse getById(Long id) {
        rules.checkIfLoanTypeExists(id);
        return mapper.loanTypeToGetLoanTypeResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public void delete(Long id) {
        rules.checkIfLoanTypeExists(id);
        repository.deleteById(id);
    }
} 