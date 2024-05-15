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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBooksTest extends BookServiceTest{

    @Test
    @DisplayName("get Books(Empty)")
    public void getAllBooks(){
        when(bookRepository.findAll()).thenReturn(new ArrayList<Book>());
        Assert.assertArrayEquals(new ArrayList<Book>().toArray(), bookService.getAllBooks().toArray());
    }

    @Test
    @DisplayName("get Books(filled)")
    public void getAllBooks1(){
        List<Book> books = new ArrayList<>();
        books.add(Book.builder().id(1l).title("book1").build());
        books.add(Book.builder().id(2l).title("book2").build());
        books.add(Book.builder().id(3l).title("book3").build());
        when(bookRepository.findAll()).thenReturn(books);
        Assert.assertArrayEquals(books.toArray(), bookService.getAllBooks().toArray());
    }
}
