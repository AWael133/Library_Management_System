package com.example.Library_Management_System.UserServiceTst;

import com.example.Library_Management_System.Entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserTest extends UserServiceTest{

    @Test
    @DisplayName("get User")
    public void getUser(){
        User user = User.builder().id(1l).username("user").build();
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        Assert.assertEquals(user, userService.getUserById(1l));
    }
}
