package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book addBook(Book book);

    Book editBook(Long id, Book book);

    void deleteBook(Long id);
}
