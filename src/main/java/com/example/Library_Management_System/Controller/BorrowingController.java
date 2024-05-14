package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Service.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class BorrowingController {

    private BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity borrowBook(@PathVariable Long bookId,
                                     @PathVariable Long patronId){
        return borrowingService.borrow(patronId, bookId);
    }

    @PutMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity returnBook(@PathVariable Long bookId,
                                     @PathVariable Long patronId){
        return borrowingService.returnBook(patronId, bookId);
    }
}
