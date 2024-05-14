package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Entity.Borrowing_Record;
import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.BookRepository;
import com.example.Library_Management_System.Repository.BorrowingRecordRepository;
import com.example.Library_Management_System.Repository.PatronRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService{
    private PatronRepository patronRepository;
    private BookRepository bookRepository;
    private BorrowingRecordRepository borrowingRecordRepository;

    public BorrowingServiceImpl(PatronRepository patronRepository,
                                BookRepository bookRepository,
                                BorrowingRecordRepository borrowingRecordRepository){
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }
    @Override
    public ResponseEntity borrow(Long patronId, Long bookId) {
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new EntityNotFoundException("Patron " + patronId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book " + bookId));

        Borrowing_Record record = Borrowing_Record.builder()
                .patron(patron)
                .book(book)
                .borrowDate(LocalDateTime.now()).build();
        if(!borrowingRecordRepository.existsByBookAndReturnDateIsNull(book))
            throw new RuntimeException("Book not available");

        borrowingRecordRepository.save(record);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity returnBook(Long patronId, Long bookId) {
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new EntityNotFoundException("Patron " + patronId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book " + bookId));

        Optional<Borrowing_Record> optionalBorrowingRecord = borrowingRecordRepository
                .findByBookAndPatronAndReturnDateIsNull(book, patron);

        Borrowing_Record record = optionalBorrowingRecord.orElseThrow(() -> new RuntimeException("can't find the borrowing record"));
        record.setReturnDate(LocalDateTime.now());
        borrowingRecordRepository.save(record);
        return ResponseEntity.ok().build();
    }
}
