package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book addBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Override
    public Book editBook(Long id, Book book) {
        Book book0 = bookRepository.findById(id).orElseThrow();
        book.setId(book0.getId());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }
}