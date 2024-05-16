package com.example.Library_Management_System.BooksServiceTest;

import com.example.Library_Management_System.Repository.BookRepository;
import com.example.Library_Management_System.Service.BookService;
import com.example.Library_Management_System.Service.BookServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;

public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    CacheManager cacheManager = new SimpleCacheManager();
    @InjectMocks
    BookService bookService = new BookServiceImpl(cacheManager, bookRepository);
}
