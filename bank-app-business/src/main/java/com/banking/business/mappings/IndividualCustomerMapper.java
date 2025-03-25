package com.banking.business.mappings;

import com.banking.business.dtos.requests.individual.CreateIndividualCustomerRequest;
import com.banking.business.dtos.responses.individual.IndividualCustomerResponse;
import com.banking.entities.IndividualCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IndividualCustomerMapper {
    
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerNumber", ignore = true)
    @Mapping(target = "creditScore", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    IndividualCustomer createRequestToEntity(CreateIndividualCustomerRequest request);

    IndividualCustomerResponse entityToResponse(IndividualCustomer customer);
} 