package com.example.Library_Management_System.BooksServiceTest;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBookTest extends BookServiceTest{

    @Test
    @DisplayName("delete Book")
    public void deleteBook(){
        Book book = Book.builder().id(1l).title("book").build();
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        try{
            bookService.deleteBook(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNull(ex);
        }

        when(bookRepository.findById(1l)).thenThrow(new EntityNotFoundException("Book"));
        try{
            bookService.deleteBook(1l);
        }catch (EntityNotFoundException ex){
            Assert.assertNotNull(ex);
        }
    }
}