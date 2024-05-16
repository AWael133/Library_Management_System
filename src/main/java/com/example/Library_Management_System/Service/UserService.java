package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    User editUser(Long id, User user);

    void deleteUser(Long id);
}
