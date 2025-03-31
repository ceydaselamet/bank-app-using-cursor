package com.banking.business.concretes;

import com.banking.business.abstracts.UserService;
import com.banking.business.constants.Messages;
import com.banking.business.rules.AuthBusinessRules;
import com.banking.core.crosscuttingconcerns.exceptions.types.BusinessException;
import com.banking.entities.User;
import com.banking.repositories.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthBusinessRules authBusinessRules;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(String.format(Messages.User.NOT_FOUND_BY_ID, id)));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(String.format(Messages.User.NOT_FOUND_BY_USERNAME, username)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        User user = getById(id);
        
        if (!user.getUsername().equals(updatedUser.getUsername())) {
            authBusinessRules.checkIfUsernameAvailable(updatedUser.getUsername());
            user.setUsername(updatedUser.getUsername());
        }
        
        if (!user.getEmail().equals(updatedUser.getEmail())) {
            authBusinessRules.checkIfEmailAvailable(updatedUser.getEmail());
            authBusinessRules.checkIfEmailValid(updatedUser.getEmail());
            user.setEmail(updatedUser.getEmail());
        }

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void changePassword(Long id, String oldPassword, String newPassword) {
        User user = getById(id);
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException(Messages.User.INCORRECT_PASSWORD);
        }
        
        authBusinessRules.checkIfPasswordValid(newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserProfile(Long id, String firstName, String lastName, String email) {
        User user = getById(id);
        
        if (!user.getEmail().equals(email)) {
            authBusinessRules.checkIfEmailAvailable(email);
            authBusinessRules.checkIfEmailValid(email);
            user.setEmail(email);
        }
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
        userRepository.save(user);
    }
} 