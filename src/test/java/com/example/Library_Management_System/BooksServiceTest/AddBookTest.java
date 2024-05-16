package com.example.Library_Management_System.BooksServiceTest;
import com.example.Library_Management_System.Entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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