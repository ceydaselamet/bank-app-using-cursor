package com.banking.business.mappings;

import com.banking.business.dtos.requests.loantypes.CreateLoanTypeRequest;
import com.banking.business.dtos.responses.loantypes.GetLoanTypeResponse;
import com.banking.entities.LoanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanTypeMapper {
    LoanTypeMapper INSTANCE = Mappers.getMapper(LoanTypeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentLoanType", ignore = true)
    @Mapping(target = "subTypes", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    LoanType createLoanTypeRequestToLoanType(CreateLoanTypeRequest request);

    @Mapping(target = "parentLoanTypeId", source = "parentLoanType.id")
    @Mapping(target = "parentLoanTypeName", source = "parentLoanType.name")
    GetLoanTypeResponse loanTypeToGetLoanTypeResponse(LoanType loanType);
} 