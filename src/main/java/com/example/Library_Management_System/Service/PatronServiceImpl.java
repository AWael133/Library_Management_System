package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService{

    String model = "Patron";

    private PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository){
        this.patronRepository = patronRepository;
    }
    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
    }

    @Override
    public Patron addPatron(Patron patron) {
        patron.setId(null);
        return patronRepository.save(patron);
    }

    @Override
    public Patron editPatron(Long id, Patron patron) {
        Patron patron0 = patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        patron.setId(patron0.getId());
        return patronRepository.save(patron);
    }

    @Override
    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        patronRepository.delete(patron);
    }
}
