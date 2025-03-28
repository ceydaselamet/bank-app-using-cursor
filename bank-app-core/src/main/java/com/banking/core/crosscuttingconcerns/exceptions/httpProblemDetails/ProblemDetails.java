package com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails {
    private String type;
    private String title;
    private String detail;
    private int status;
    private String instance;
} 