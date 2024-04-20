package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrons")
@Validated
public class PatronController {
    private PatronService patronService;

    public PatronController(PatronService patronService){
        this.patronService = patronService;
    }
    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons(){
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id){
        return ResponseEntity.ok(patronService.getPatronById(id));
    }

    @PostMapping()
    public ResponseEntity<Patron> addPatron(@RequestBody @Valid Patron patron){
        return ResponseEntity.ok(patronService.addPatron(patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> editPatron(@PathVariable Long id,
                                             @RequestBody @Valid Patron patron){
        return ResponseEntity.ok(patronService.editPatron(id, patron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatron(@PathVariable Long id){
        patronService.deletePatron(id);
        return ResponseEntity.ok().build();
    }
}
