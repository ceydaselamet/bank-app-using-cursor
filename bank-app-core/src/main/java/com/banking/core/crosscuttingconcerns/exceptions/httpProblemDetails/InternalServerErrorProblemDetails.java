package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InternalServerErrorProblemDetails extends ProblemDetails {
    public InternalServerErrorProblemDetails(String detail) {
        setTitle("Internal Server Error");
        setDetail(detail);
        setType("https://example.com/probs/internal");
        setStatus(500);
    }
} 