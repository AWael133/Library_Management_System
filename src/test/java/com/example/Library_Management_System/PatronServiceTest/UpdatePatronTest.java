package com.example.Library_Management_System.PatronServiceTest;
import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Repository.PatronRepository;
import com.example.Library_Management_System.Service.PatronService;
import com.example.Library_Management_System.Service.PatronServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdatePatronTest extends PatronServiceTest{

    @Test
    @DisplayName("edit Patron")
    public void editPatron(){
        Patron patron0 = Patron.builder().id(1l).name("patron0").build();
        Patron patron1 = Patron.builder().name("patron1").build();
        Patron patron2 = Patron.builder().id(1l).name("patron1").build();
        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron0));
        when(patronRepository.save(patron1)).thenReturn(patron2);
        Assert.assertEquals(patron2, patronService.editPatron(1l, patron1));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("edit Patron (duplicate name)")
    public void editPatronWithExistName(){
        Patron patron0 = Patron.builder().name("patron0").build();
        Patron patron1 = Patron.builder().id(1l).name("patron1").build();
        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron1));
        when(patronRepository.existsByNameAndIdNot(patron0.getName(), 1l)).thenReturn(true);
        patronService.editPatron(1l, patron0);
    }
}