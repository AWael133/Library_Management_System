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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetPatronsTest extends PatronServiceTest{

    @Test
    @DisplayName("get Patrons(Empty)")
    public void getAllPatrons(){
        when(patronRepository.findAll()).thenReturn(new ArrayList<Patron>());
        Assert.assertArrayEquals(new ArrayList<Patron>().toArray(), patronService.getAllPatrons().toArray());
    }

    @Test
    @DisplayName("get Patrons(filled)")
    public void getAllPatrons1(){
        List<Patron> patrons = new ArrayList<>();
        patrons.add(Patron.builder().id(1l).name("patron1").build());
        patrons.add(Patron.builder().id(2l).name("patron2").build());
        patrons.add(Patron.builder().id(3l).name("patron3").build());
        when(patronRepository.findAll()).thenReturn(patrons);
        Assert.assertArrayEquals(patrons.toArray(), patronService.getAllPatrons().toArray());
    }
}
