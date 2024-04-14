package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.Patron;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class BorrowingController {

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity borrowBook(@PathVariable Long bookId,
                                             @PathVariable Long patronId){
        return null;
    }

    @PutMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity returnBook(@PathVariable Long bookId,
                                             @PathVariable Long patronId){
        return null;
    }
}
