package com.example.Library_Management_System.UserServiceTst;
import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Repository.UserRepository;
import com.example.Library_Management_System.Service.UserService;
import com.example.Library_Management_System.Service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserTest {

    @Mock
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);;
    @InjectMocks
    private UserService userService = new UserServiceImpl(passwordEncoder, userRepository);

    @Test
    @DisplayName("edit User")
    public void editUser(){
        User user0 = User.builder()
                .id(1l)
                .username("user0")
                .password(passwordEncoder.encode("pass")).build();
        User user1 = User.builder()
                .username("user1")
                .password("pass").build();
        User user2 = User.builder()
                .id(1l)
                .username("user1")
                .password(passwordEncoder.encode("pass")).build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user0));
        when(userRepository.save(user1)).thenReturn(user2);
        Assert.assertEquals(user2, userService.editUser(1l, user1));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("edit User (duplicate name)")
    public void editUserWithExistName(){
        User user0 = User.builder()
                .id(1l)
                .username("user0")
                .password(passwordEncoder.encode("pass")).build();
        User user1 = User.builder()
                .username("user1")
                .password("pass").build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user0));
        when(userRepository.existsByUsernameAndIdNot(user1.getUsername(), 1l)).thenReturn(true);
        userService.editUser(1l, user1);
    }
}