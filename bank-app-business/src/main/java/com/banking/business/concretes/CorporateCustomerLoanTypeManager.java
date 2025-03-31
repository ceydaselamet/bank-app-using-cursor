package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateCustomerLoanTypeService;
import com.banking.business.dtos.requests.loantypes.CreateCorporateCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetCorporateCustomerLoanTypeResponse;
import com.banking.business.mappings.CorporateCustomerLoanTypeMapper;
import com.banking.business.rules.CorporateCustomerLoanTypeBusinessRules;
import com.banking.core.utils.paging.PageDto;
import com.banking.repositories.abstracts.CorporateCustomerLoanTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateCustomerLoanTypeManager implements CorporateCustomerLoanTypeService {
    private final CorporateCustomerLoanTypeRepository repository;
    private final CorporateCustomerLoanTypeMapper mapper;
    private final CorporateCustomerLoanTypeBusinessRules rules;

    @Override
    @Transactional
    public GetCorporateCustomerLoanTypeResponse create(CreateCorporateCustomerLoanTypeRequest request) {
        rules.validateAmountRange(request.getMinAmount(), request.getMaxAmount());
        rules.validateTermRange(request.getMinTerm(), request.getMaxTerm());
        rules.validateYearsInBusiness(request.getMinYearsInBusiness());
        rules.validateAnnualRevenue(request.getMinAnnualRevenue());

        var entity = mapper.createRequestToEntity(request);
        
        // Explicitly set ID to null to ensure a new record is created
        entity.setId(null);
        
        return mapper.entityToResponse(repository.save(entity));
    }

    @Override
    public List<GetCorporateCustomerLoanTypeResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<GetCorporateCustomerLoanTypeResponse> getAllPaginated(Pageable pageable) {
        Page<GetCorporateCustomerLoanTypeResponse> page = repository.findAll(pageable)
                .map(mapper::entityToResponse);
        return PageDto.of(page);
    }

    @Override
    public GetCorporateCustomerLoanTypeResponse getById(Long id) {
        rules.checkIfExists(id);
        return mapper.entityToResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public List<GetCorporateCustomerLoanTypeResponse> getByCustomerId(Long customerId) {
        return repository.findByCustomer_Id(customerId).stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByCustomerIdPaginated(Long customerId, Pageable pageable) {
        Page<GetCorporateCustomerLoanTypeResponse> page = repository.findByCustomer_Id(customerId, pageable)
                .map(mapper::entityToResponse);
        return PageDto.of(page);
    }

    @Override
    public List<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenue(BigDecimal annualRevenue) {
        return repository.findByMinAnnualRevenueLessThanEqual(annualRevenue).stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByMinAnnualRevenuePaginated(BigDecimal annualRevenue, Pageable pageable) {
        Page<GetCorporateCustomerLoanTypeResponse> page = repository.findByMinAnnualRevenueLessThanEqual(annualRevenue, pageable)
                .map(mapper::entityToResponse);
        return PageDto.of(page);
    }

    @Override
    public List<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusiness(Integer yearsInBusiness) {
        return repository.findByMinYearsInBusinessLessThanEqual(yearsInBusiness).stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageDto<GetCorporateCustomerLoanTypeResponse> getByMinYearsInBusinessPaginated(Integer yearsInBusiness, Pageable pageable) {
        Page<GetCorporateCustomerLoanTypeResponse> page = repository.findByMinYearsInBusinessLessThanEqual(yearsInBusiness, pageable)
                .map(mapper::entityToResponse);
        return PageDto.of(page);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfExists(id);
        repository.deleteById(id);
    }
} 