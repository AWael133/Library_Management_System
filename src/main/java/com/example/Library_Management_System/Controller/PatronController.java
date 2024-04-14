package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Entity.Patron;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id){
        return null;
    }

    @PostMapping()
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> editPatron(@PathVariable Long id,
                                             @RequestBody Patron patron){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatron(@PathVariable Long id){
        return null;
    }
}
