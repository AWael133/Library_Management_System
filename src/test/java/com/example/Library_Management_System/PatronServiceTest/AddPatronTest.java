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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddPatronTest{

    @Mock
    private PatronRepository patronRepository;
    @InjectMocks
    private PatronService patronService = new PatronServiceImpl(patronRepository);

    @Test
    @DisplayName("add Patron")
    public void addPatron(){
        Patron patron0 = Patron.builder().name("patron1").build();
        Patron patron = Patron.builder().id(1l).name("patron1").build();
        when(patronRepository.save(patron0)).thenReturn(patron);
        Assert.assertEquals(patron, patronService.addPatron(patron0));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("add Patron (duplicate name)")
    public void addPatronWithExistName(){
        Patron patron0 = Patron.builder().name("patron1").build();
        when(patronRepository.existsByName("patron1")).thenReturn(true);
        patronService.addPatron(patron0);
    }
}