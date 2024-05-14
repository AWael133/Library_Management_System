package com.example.Library_Management_System.UserServiceTst;

import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
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
public class DeleteUserTest {

    @Mock
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);;
    @InjectMocks
    private UserService userService = new UserServiceImpl(passwordEncoder, userRepository);

    @Test
    @DisplayName("delete User")
    public void deleteUser(){
        User user = User.builder().id(1l).username("user").build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        try{
            userService.deleteUser(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNull(ex);
        }

        when(userRepository.findById(1l)).thenThrow(new EntityNotFoundException("User"));
        try{
            userService.deleteUser(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNotNull(ex);
        }
    }
}