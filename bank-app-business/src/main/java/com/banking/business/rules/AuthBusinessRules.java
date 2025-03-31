package com.banking.business.rules;

import com.banking.business.constants.Messages;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.repositories.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthBusinessRules {

    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public void checkIfUserExists(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new BusinessException(String.format(Messages.User.NOT_FOUND_BY_USERNAME, username));
        }
    }

    public void checkIfUsernameAvailable(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(Messages.Auth.USERNAME_TAKEN);
        }
    }

    public void checkIfEmailAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(Messages.Auth.EMAIL_TAKEN);
        }
    }

    public void checkIfPasswordValid(String password) {
        if (password == null || password.length() < 6) {
            throw new BusinessException(Messages.Auth.INVALID_PASSWORD);
        }
    }

    public void checkIfEmailValid(String email) {
        if (email == null || !pattern.matcher(email).matches()) {
            throw new BusinessException(Messages.Auth.INVALID_EMAIL);
        }
    }
} 