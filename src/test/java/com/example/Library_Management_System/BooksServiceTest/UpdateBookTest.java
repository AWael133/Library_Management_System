package com.example.Library_Management_System.BooksServiceTest;
import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Repository.BookRepository;
import com.example.Library_Management_System.Service.BookService;
import com.example.Library_Management_System.Service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateBookTest{

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService = new BookServiceImpl(bookRepository);

    @Test
    @DisplayName("edit Book")
    public void editBook(){
        Book book0 = Book.builder().id(1l).title("book0").build();
        Book book1 = Book.builder().title("book1").build();
        Book book2 = Book.builder().id(1l).title("book1").build();
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book0));
        when(bookRepository.save(book1)).thenReturn(book2);
        Assert.assertEquals(book2, bookService.editBook(1l, book1));
    }
}