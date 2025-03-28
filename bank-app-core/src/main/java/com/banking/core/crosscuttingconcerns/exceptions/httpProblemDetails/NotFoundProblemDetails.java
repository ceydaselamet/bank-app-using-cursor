package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundProblemDetails extends ProblemDetails {
    public NotFoundProblemDetails(String detail) {
        setTitle("Resource Not Found");
        setDetail(detail);
        setType("https://example.com/probs/not-found");
        setStatus(404);
    }
} 