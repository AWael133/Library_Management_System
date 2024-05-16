package com.example.Library_Management_System;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Entity.Borrowing_Record;
import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Repository.BookRepository;
import com.example.Library_Management_System.Repository.BorrowingRecordRepository;
import com.example.Library_Management_System.Repository.PatronRepository;
import com.example.Library_Management_System.Service.BorrowingService;
import com.example.Library_Management_System.Service.BorrowingServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingServiceTest {

    @Mock
    private PatronRepository patronRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;
    @InjectMocks
    private BorrowingService borrowingService = new BorrowingServiceImpl(patronRepository,
            bookRepository,
            borrowingRecordRepository);

    @Test
    public void borrow() {
        long patronId = 1;
        long bookId = 1;

        Patron patron = Patron.builder()
                .id(1l)
                .name("patron").build();
        Book book = Book.builder()
                .id(1l)
                .title("book").build();

        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.existsByBookAndReturnDateIsNull(book)).thenReturn(false);

        Assert.assertEquals(borrowingService.borrow(patronId, bookId).getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("borrow unavailable book")
    public void borrowInvalid() {
        long patronId = 1;
        long bookId = 1;

        Patron patron = Patron.builder()
                .id(1l)
                .name("patron").build();
        Book book = Book.builder()
                .id(1l)
                .title("book").build();

        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.existsByBookAndReturnDateIsNull(book)).thenReturn(true);

        borrowingService.borrow(patronId, bookId);
    }

    @Test
    public void returnBook() {
        long patronId = 1;
        long bookId = 1;

        Patron patron = Patron.builder()
                .id(1l)
                .name("patron").build();
        Book book = Book.builder()
                .id(1l)
                .title("book").build();
        Borrowing_Record record = Borrowing_Record.builder()
                .book(book)
                .patron(patron)
                .borrowDate(LocalDateTime.now()).build();

        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)).thenReturn(Optional.of(record));

        Assert.assertEquals(borrowingService.returnBook(patronId, bookId).getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("return unavailable book")
    public void returnInvalidBook() {
        long patronId = 1;
        long bookId = 1;

        Patron patron = Patron.builder()
                .id(1l)
                .name("patron").build();
        Book book = Book.builder()
                .id(1l)
                .title("book").build();

        when(patronRepository.findById(1l)).thenReturn(Optional.of(patron));
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)).thenReturn(Optional.empty());

        borrowingService.returnBook(patronId, bookId);
    }
}
