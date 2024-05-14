package com.example.Library_Management_System.Service;

import org.springframework.http.ResponseEntity;

public interface BorrowingService {
    ResponseEntity borrow(Long patronId, Long bookId);

    ResponseEntity returnBook(Long patronId, Long bookId);
}
