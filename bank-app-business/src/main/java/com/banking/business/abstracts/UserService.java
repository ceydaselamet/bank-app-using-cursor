package com.banking.business.abstracts;

import com.banking.entities.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    List<User> getAllUsers();
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    void changePassword(Long id, String oldPassword, String newPassword);
    void updateUserProfile(Long id, String firstName, String lastName, String email);
} 