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
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddBookTest extends BookServiceTest{

    @Test
    @DisplayName("add Book")
    public void addBook(){
        Book book0 = Book.builder().title("book1").build();
        Book book = Book.builder().id(1l).title("book1").build();
        when(bookRepository.save(book0)).thenReturn(book);
        Assert.assertEquals(book, bookService.addBook(book0));
    }
}