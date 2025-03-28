package com.banking.core.crosscuttingconcerns.exceptions.handlers;

import com.banking.core.crosscuttingconcerns.exceptions.httpProblemDetails.*;
import com.banking.core.crosscuttingconcerns.exceptions.types.AuthorizationException;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HttpExceptionHandler extends com.banking.core.crosscuttingconcerns.exceptions.handlers.ExceptionHandler {

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleException(Exception exception) {
        return new InternalServerErrorProblemDetails(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException(BusinessException exception) {
        return new BusinessProblemDetails(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetails handleAuthorizationException(AuthorizationException exception) {
        return new AuthorizationProblemDetails(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
            validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        return new ValidationProblemDetails(validationErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetails handleNotFoundException(RuntimeException exception) {
        return new NotFoundProblemDetails(exception.getMessage());
    }
} 