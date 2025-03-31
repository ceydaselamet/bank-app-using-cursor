package com.banking.business.mappings;

import com.banking.business.dtos.requests.loanapplications.CreateLoanApplicationRequest;
import com.banking.business.dtos.responses.loanapplications.GetLoanApplicationResponse;
import com.banking.entities.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "individualCustomerLoanType", ignore = true)
    @Mapping(target = "corporateCustomerLoanType", ignore = true)
    @Mapping(target = "individualCustomer", ignore = true)
    @Mapping(target = "corporateCustomer", ignore = true)
    @Mapping(target = "interestRate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    @Mapping(target = "monthlyPayment", ignore = true)
    @Mapping(target = "totalPayment", ignore = true)
    @Mapping(target = "decisionDate", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    LoanApplication createLoanApplicationRequestToLoanApplication(CreateLoanApplicationRequest request);

    @Mapping(target = "loanTypeId", expression = "java(getLoanTypeId(loanApplication))")
    @Mapping(target = "loanTypeName", expression = "java(getLoanTypeName(loanApplication))")
    @Mapping(target = "customerId", expression = "java(getCustomerId(loanApplication))")
    @Mapping(target = "customerName", expression = "java(getCustomerName(loanApplication))")
    @Mapping(target = "customerNumber", expression = "java(getCustomerNumber(loanApplication))")
    GetLoanApplicationResponse loanApplicationToGetLoanApplicationResponse(LoanApplication loanApplication);

    default Long getLoanTypeId(LoanApplication application) {
        if (application.getIndividualCustomerLoanType() != null) {
            return application.getIndividualCustomerLoanType().getId();
        }
        return application.getCorporateCustomerLoanType() != null ? 
               application.getCorporateCustomerLoanType().getId() : null;
    }

    default String getLoanTypeName(LoanApplication application) {
        if (application.getIndividualCustomerLoanType() != null) {
            return application.getIndividualCustomerLoanType().getName();
        }
        return application.getCorporateCustomerLoanType() != null ? 
               application.getCorporateCustomerLoanType().getName() : null;
    }

    default Long getCustomerId(LoanApplication application) {
        if (application.getIndividualCustomer() != null) {
            return application.getIndividualCustomer().getId();
        }
        return application.getCorporateCustomer() != null ? 
               application.getCorporateCustomer().getId() : null;
    }

    default String getCustomerName(LoanApplication application) {
        if (application.getIndividualCustomer() != null) {
            return application.getIndividualCustomer().getFirstName() + " " + 
                   application.getIndividualCustomer().getLastName();
        }
        return application.getCorporateCustomer() != null ? 
               application.getCorporateCustomer().getCompanyName() : null;
    }

    default String getCustomerNumber(LoanApplication application) {
        if (application.getIndividualCustomer() != null) {
            return application.getIndividualCustomer().getCustomerNumber();
        }
        return application.getCorporateCustomer() != null ? 
               application.getCorporateCustomer().getCustomerNumber() : null;
    }
} 