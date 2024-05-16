package com.example.Library_Management_System.UserServiceTst;

import com.example.Library_Management_System.Entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTest extends UserServiceTest{

    @Test
    @DisplayName("get Users(Empty)")
    public void getAllUsers(){
        when(userRepository.findAll()).thenReturn(new ArrayList<User>());
        Assert.assertArrayEquals(new ArrayList<User>().toArray(), userService.getAllUsers().toArray());
    }

    @Test
    @DisplayName("get Users(filled)")
    public void getAllUsers1(){
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1l).username("user1").build());
        users.add(User.builder().id(2l).username("user2").build());
        users.add(User.builder().id(3l).username("user3").build());
        when(userRepository.findAll()).thenReturn(users);
        Assert.assertArrayEquals(users.toArray(), userService.getAllUsers().toArray());
    }
}
