package com.example.Library_Management_System.PatronServiceTest;

import com.example.Library_Management_System.Entity.Patron;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetPatronTest extends PatronServiceTest{

    @Test
    @DisplayName("get Patron")
    public void getPatron(){
        Patron patron = Patron.builder().id(1l).name("patron").build();
        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        Assert.assertEquals(patron, patronService.getPatronById(1l));
    }
}
