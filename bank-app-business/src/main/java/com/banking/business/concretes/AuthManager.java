package com.banking.business.concretes;

import com.banking.business.abstracts.AuthService;
import com.banking.business.constants.Messages;
import com.banking.business.dtos.requests.auth.LoginRequest;
import com.banking.business.dtos.requests.auth.RegisterRequest;
import com.banking.business.dtos.responses.auth.JwtResponse;
import com.banking.business.rules.AuthBusinessRules;
import com.banking.core.security.JwtTokenService;
import com.banking.entities.Role;
import com.banking.entities.User;
import com.banking.repositories.abstracts.RoleRepository;
import com.banking.repositories.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService, UserDetailsService {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthBusinessRules authBusinessRules;

    @Override
    public JwtResponse login(LoginRequest request) {
        validateUserCredentials(request.getUsername(), request.getPassword());

        try {
            Authentication authentication = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            User user = (User) authentication.getPrincipal();
            String jwt = jwtTokenService.generateToken(user);

            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());

            return new JwtResponse(jwt, user.getId(), user.getUsername(), user.getEmail(), roles);
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    private AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    @Transactional
    public JwtResponse register(RegisterRequest request) {
        validateRegistrationRules(request);

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException(Messages.Auth.DEFAULT_ROLE_NOT_FOUND));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        return new JwtResponse(null, user.getId(), user.getUsername(), user.getEmail(),
                Collections.singletonList("ROLE_USER"));
    }

    @Override
    public void validateUserCredentials(String username, String password) {
        authBusinessRules.checkIfUserExists(username);
        authBusinessRules.checkIfPasswordValid(password);
    }

    @Override
    public void validateRegistrationRules(RegisterRequest request) {
        authBusinessRules.checkIfUsernameAvailable(request.getUsername());
        authBusinessRules.checkIfEmailAvailable(request.getEmail());
        authBusinessRules.checkIfPasswordValid(request.getPassword());
        authBusinessRules.checkIfEmailValid(request.getEmail());
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                    String.format(Messages.User.NOT_FOUND_BY_USERNAME, username)));
    }
} 