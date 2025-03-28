package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizationProblemDetails extends ProblemDetails {
    public AuthorizationProblemDetails(String detail) {
        setTitle("Authorization Error");
        setDetail(detail);
        setType("https://example.com/probs/authorization");
        setStatus(401);
    }
} 