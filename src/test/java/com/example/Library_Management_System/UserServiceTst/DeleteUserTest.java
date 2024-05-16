package com.example.Library_Management_System.UserServiceTst;

import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserTest extends UserServiceTest{

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