package com.banking.business.mappings;

import com.banking.business.dtos.requests.individual.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.individual.IndividualCustomerResponse;
import com.banking.entities.IndividualCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndividualCustomerMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerNumber", ignore = true)
    @Mapping(target = "creditScore", ignore = true)
    @Mapping(target = "active", constant = "true")
    IndividualCustomer createRequestToEntity(CreateIndividualCustomerRequest request);
    
    IndividualCustomerResponse entityToResponse(IndividualCustomer customer);
} 