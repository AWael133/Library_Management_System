package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@Validated
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book){
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,
                                         @RequestBody @Valid Book book){
        return ResponseEntity.ok(bookService.editBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
