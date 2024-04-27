package com.example.Library_Management_System.PatronServiceTest;


import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
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
public class DeletePatronTest{

    @Mock
    private PatronRepository patronRepository;
    @InjectMocks
    private PatronService patronService = new PatronServiceImpl(patronRepository);

    @Test
    @DisplayName("delete Patron")
    public void deletePatron(){
        Patron patron = Patron.builder().id(1l).name("patron").build();
        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        try{
            patronService.deletePatron(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNull(ex);
        }

        when(patronRepository.findById(1l)).thenThrow(new EntityNotFoundException("Patron"));
        try{
            patronService.deletePatron(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNotNull(ex);
        }
    }
}