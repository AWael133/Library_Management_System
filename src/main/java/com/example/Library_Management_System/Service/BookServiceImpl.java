package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Book;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.BookRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final CacheManager cacheManager;
    final String BOOK_INFO_CACHE = "patronInfo";

    String model = "Book";

    private BookRepository bookRepository;

    public BookServiceImpl(CacheManager cacheManager,
                           BookRepository bookRepository){
        this.cacheManager = cacheManager;
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value=BOOK_INFO_CACHE, key="#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
    }

    @Override
    public Book addBook(Book book) {
        book.setId(null);
        book = bookRepository.save(book);

        Cache bookInfoCache = cacheManager.getCache(BOOK_INFO_CACHE);
        if(bookInfoCache != null)
            bookInfoCache.put(book.getId(), book);
        return book;
    }

    @Override
    @CachePut(value=BOOK_INFO_CACHE, key="#id")
    public Book editBook(Long id, Book book) {
        Book book0 = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        book.setId(book0.getId());
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        bookRepository.delete(book);

        Cache bookInfoCache = cacheManager.getCache(BOOK_INFO_CACHE);
        if(bookInfoCache != null)
            bookInfoCache.evictIfPresent(id);
    }
}
