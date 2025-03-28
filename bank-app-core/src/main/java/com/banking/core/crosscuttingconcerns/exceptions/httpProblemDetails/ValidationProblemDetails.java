package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> validationErrors;

    public ValidationProblemDetails(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
        setTitle("Validation Error(s)");
        setDetail("One or more validation errors occurred");
        setType("https://example.com/probs/validation");
        setStatus(400);
    }
} 