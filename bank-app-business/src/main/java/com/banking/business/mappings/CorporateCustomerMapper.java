package com.banking.business.mappings;

import com.banking.business.dtos.requests.corporate.CreateCorporateCustomerRequest;
import com.banking.business.dtos.responses.corporate.CorporateCustomerResponse;
import com.banking.entities.CorporateCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateCustomerMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerNumber", ignore = true)
    @Mapping(target = "creditRating", ignore = true)
    @Mapping(target = "active", constant = "true")
    CorporateCustomer createRequestToEntity(CreateCorporateCustomerRequest request);
    
    CorporateCustomerResponse entityToResponse(CorporateCustomer customer);
} 