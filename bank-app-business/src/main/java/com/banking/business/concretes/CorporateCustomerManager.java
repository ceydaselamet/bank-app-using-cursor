package com.banking.business.concretes;

import com.banking.business.abstracts.CorporateCustomerService;
import com.banking.business.abstracts.CustomerService;
import com.banking.business.constants.CustomerConstants;
import com.banking.business.dtos.requests.corporate.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.corporate.CorporateCustomerResponse;
import com.banking.business.mappings.CorporateCustomerMapper;
import com.banking.business.rules.CorporateCustomerBusinessRules;
import com.banking.core.exceptions.BusinessException;
import com.banking.entities.CorporateCustomer;
import com.banking.repositories.abstracts.CorporateCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CorporateCustomerBusinessRules rules;
    private final CustomerService customerService;
    private final CorporateCustomerMapper mapper;

    @Override
    @Transactional
    public CorporateCustomerResponse add(CreateCorporateCustomerRequest request) {
        rules.checkIfTaxNumberExists(request.getTaxNumber());
        rules.checkIfEmailExists(request.getEmail());
        rules.checkMinimumCompanyAge(calculateCompanyAge(request.getEstablishmentDate()));
        rules.checkMinimumAnnualRevenue(request.getAnnualRevenue());

        CorporateCustomer customer = mapper.createRequestToEntity(request);
        customer.setCustomerNumber(customerService.generateCustomerNumber(CustomerConstants.CORPORATE_CUSTOMER_PREFIX));
        customer.setCreditRating(determineInitialCreditRating(request.getAnnualRevenue(), request.getCompanySize()));

        CorporateCustomer savedCustomer = corporateCustomerRepository.save(customer);
        return mapper.entityToResponse(savedCustomer);
    }

    @Override
    public CorporateCustomerResponse getById(Long id) {
        CorporateCustomer customer = corporateCustomerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CustomerConstants.CORPORATE_CUSTOMER_NOT_FOUND));
        return mapper.entityToResponse(customer);
    }

    @Override
    public CorporateCustomerResponse getByTaxNumber(String taxNumber) {
        CorporateCustomer customer = corporateCustomerRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new BusinessException(CustomerConstants.CORPORATE_CUSTOMER_NOT_FOUND));
        return mapper.entityToResponse(customer);
    }

    @Override
    public List<CorporateCustomerResponse> getAll() {
        return corporateCustomerRepository.findAll().stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public List<CorporateCustomerResponse> getAllByTaxOffice(String taxOffice) {
        return corporateCustomerRepository.findByTaxOffice(taxOffice).stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public List<CorporateCustomerResponse> getAllByMinAnnualRevenue(Double minRevenue) {
        return corporateCustomerRepository.findCustomersWithMinAnnualRevenue(minRevenue).stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    public List<CorporateCustomerResponse> getAllByCompanySizeAndCreditRating(Integer minSize, String rating) {
        return corporateCustomerRepository.findByCompanySizeAndCreditRating(minSize, rating).stream()
                .map(mapper::entityToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerService.checkIfCustomerExists(id);
        corporateCustomerRepository.deleteById(id);
    }

    private int calculateCompanyAge(LocalDate establishmentDate) {
        return Period.between(establishmentDate, LocalDate.now()).getYears();
    }

    private String determineInitialCreditRating(Double annualRevenue, Integer companySize) {
        if (annualRevenue >= 10_000_000 && companySize >= 100) {
            return CustomerConstants.CREDIT_RATING_A;
        } else if (annualRevenue >= 5_000_000 && companySize >= 50) {
            return CustomerConstants.CREDIT_RATING_B;
        } else if (annualRevenue >= 1_000_000 && companySize >= 10) {
            return CustomerConstants.CREDIT_RATING_C;
        } else {
            return CustomerConstants.CREDIT_RATING_D;
        }
    }
} 