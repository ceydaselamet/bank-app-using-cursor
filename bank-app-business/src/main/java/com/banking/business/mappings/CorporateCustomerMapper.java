package com.banking.business.mappings;

import com.banking.business.dtos.requests.corporate.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.corporate.CorporateCustomerResponse;
import com.banking.entities.CorporateCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CorporateCustomerMapper {
    
    CorporateCustomerMapper INSTANCE = Mappers.getMapper(CorporateCustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerNumber", ignore = true)
    @Mapping(target = "creditRating", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    CorporateCustomer createRequestToEntity(CreateCorporateCustomerRequest request);

    CorporateCustomerResponse entityToResponse(CorporateCustomer customer);
} 