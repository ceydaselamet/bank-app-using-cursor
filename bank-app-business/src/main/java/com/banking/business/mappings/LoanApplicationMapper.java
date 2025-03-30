package com.banking.business.mappings;

import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.entities.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
    LoanApplicationMapper INSTANCE = Mappers.getMapper(LoanApplicationMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loanType", ignore = true)
    @Mapping(target = "individualCustomer", ignore = true)
    @Mapping(target = "corporateCustomer", ignore = true)
    @Mapping(target = "interestRate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    @Mapping(target = "monthlyPayment", ignore = true)
    @Mapping(target = "totalPayment", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "decisionDate", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    LoanApplication createLoanApplicationRequestToLoanApplication(CreateLoanApplicationRequest request);

    @Mapping(target = "loanTypeId", source = "loanType.id")
    @Mapping(target = "loanTypeName", source = "loanType.name")
    @Mapping(target = "customerId", expression = "java(loanApplication.getIndividualCustomer() != null ? loanApplication.getIndividualCustomer().getId() : loanApplication.getCorporateCustomer().getId())")
    @Mapping(target = "customerName", expression = "java(loanApplication.getIndividualCustomer() != null ? loanApplication.getIndividualCustomer().getFirstName() + ' ' + loanApplication.getIndividualCustomer().getLastName() : loanApplication.getCorporateCustomer().getCompanyName())")
    GetLoanApplicationResponse loanApplicationToGetLoanApplicationResponse(LoanApplication loanApplication);
} 