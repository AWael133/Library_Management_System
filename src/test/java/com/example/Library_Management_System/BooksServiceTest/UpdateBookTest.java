package com.example.Library_Management_System.BooksServiceTest;
import com.example.Library_Management_System.Entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateBookTest extends BookServiceTest{

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