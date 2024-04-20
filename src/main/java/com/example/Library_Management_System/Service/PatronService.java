package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Patron;

import java.util.List;

public interface PatronService {
    List<Patron> getAllPatrons();

    Patron getPatronById(Long id);

    Patron addPatron(Patron patron);

    Patron editPatron(Long id, Patron patron);

    void deletePatron(Long id);
}
