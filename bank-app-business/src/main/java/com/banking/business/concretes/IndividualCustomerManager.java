package com.banking.business.concretes;

import com.banking.business.abstracts.CustomerService;
import com.banking.business.abstracts.IndividualCustomerService;
import com.banking.business.constants.Messages.ValidationMessages;
import com.banking.business.constants.Messages.CustomerFormat;
import com.banking.business.constants.Messages.CreditScoring;
import com.banking.business.dtos.requests.individual.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.individual.IndividualCustomerResponse;
import com.banking.business.mappings.IndividualCustomerMapper;
import com.banking.business.rules.IndividualCustomerBusinessRules;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.core.utils.paging.PageDto;
import com.banking.entities.IndividualCustomer;
import com.banking.repositories.abstracts.IndividualCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules rules;
    private final CustomerService customerService;
    private final IndividualCustomerMapper mapper;

    @Override
    @Transactional
    public IndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        rules.checkIfNationalIdExists(request.getNationalId());
        rules.checkIfEmailExists(request.getEmail());
        rules.checkMinimumAge(calculateAge(request.getBirthDate()));

        IndividualCustomer customer = mapper.createRequestToEntity(request);
        customer.setCustomerNumber(customerService.generateCustomerNumber(CustomerFormat.INDIVIDUAL_PREFIX));
        customer.setCreditScore(generateInitialCreditScore());

        IndividualCustomer savedCustomer = individualCustomerRepository.save(customer);
        return mapper.entityToResponse(savedCustomer);
    }

    @Override
    public IndividualCustomerResponse getById(Long id) {
        IndividualCustomer customer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ValidationMessages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        return mapper.entityToResponse(customer);
    }

    @Override
    public IndividualCustomerResponse getByNationalId(String nationalId) {
        IndividualCustomer customer = individualCustomerRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new BusinessException(ValidationMessages.INDIVIDUAL_CUSTOMER_NOT_FOUND));
        return mapper.entityToResponse(customer);
    }

    @Override
    public List<IndividualCustomerResponse> getAll() {
        return individualCustomerRepository.findAll().stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public List<IndividualCustomerResponse> getAllByMinCreditScore(Integer minScore) {
        return individualCustomerRepository.findCustomersWithMinCreditScore(minScore).stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public List<IndividualCustomerResponse> getAllByIncomeRange(Double minIncome, Double maxIncome) {
        return individualCustomerRepository.findCustomersByIncomeRange(minIncome, maxIncome).stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerService.checkIfCustomerExists(id);
        individualCustomerRepository.deleteById(id);
    }

    @Override
    public PageDto<IndividualCustomerResponse> getAllPaginated(Pageable pageable) {
        Page<IndividualCustomerResponse> page = individualCustomerRepository.findAll(pageable)
                .map(mapper::entityToResponse);
        return PageDto.of(page);
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private int generateInitialCreditScore() {
        return CreditScoring.MIN_SCORE + 
               (int) (Math.random() * (CreditScoring.GOOD_SCORE - CreditScoring.MIN_SCORE));
    }
} 