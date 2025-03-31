package com.banking.business.mappings;

import com.banking.business.dtos.requests.loantypes.CreateIndividualCustomerLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetIndividualCustomerLoanTypeResponse;
import com.banking.entities.IndividualCustomerLoanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IndividualCustomerLoanTypeMapper {
    @Mapping(source = "baseInterestRate", target = "interestRate")
    IndividualCustomerLoanType createRequestToIndividualCustomerLoanType(CreateIndividualCustomerLoanTypeRequest request);

    @Mapping(source = "interestRate", target = "baseInterestRate")
    GetIndividualCustomerLoanTypeResponse individualCustomerLoanTypeToGetResponse(IndividualCustomerLoanType loanType);
} 