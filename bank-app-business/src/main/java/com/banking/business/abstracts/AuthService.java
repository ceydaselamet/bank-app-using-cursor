package com.banking.business.abstracts;

import com.banking.business.dtos.requests.auth.LoginRequest;
import com.banking.business.dtos.requests.auth.RegisterRequest;
import com.banking.business.dtos.responses.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest request);
    JwtResponse register(RegisterRequest request);
    void validateUserCredentials(String username, String password);
    void validateRegistrationRules(RegisterRequest request);
    boolean isEmailAvailable(String email);
    boolean isUsernameAvailable(String username);
} 