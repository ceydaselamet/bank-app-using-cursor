package com.banking.business.mappings;

import com.banking.business.dtos.requests.loantypes.CreateCorporateCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetCorporateCustomerLoanTypeResponse;
import com.banking.entities.CorporateCustomerLoanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateCustomerLoanTypeMapper {

    @Mapping(target = "id", ignore = true)
    CorporateCustomerLoanType createRequestToEntity(CreateCorporateCustomerLoanTypeRequest request);

    GetCorporateCustomerLoanTypeResponse entityToResponse(CorporateCustomerLoanType loanType);
} 