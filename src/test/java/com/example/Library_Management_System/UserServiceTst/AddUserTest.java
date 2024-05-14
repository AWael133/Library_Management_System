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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddUserTest {

    @Mock
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @InjectMocks
    private UserService userService = new UserServiceImpl(passwordEncoder, userRepository);

    @Test
    @DisplayName("add User")
    public void addUser(){
        User user0 = User.builder()
                .username("user1")
                .password("pass").build();
        User user = User.builder()
                .id(1l)
                .username("user1")
                .password("pass").build();
        when(userRepository.save(user0)).thenReturn(user);
        Assert.assertEquals(user, userService.addUser(user0));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("add User (duplicate username)")
    public void addUserWithExistUsername(){
        User user0 = User.builder().username("user1").build();
        when(userRepository.existsByUsername("user1")).thenReturn(true);
        userService.addUser(user0);
    }
}