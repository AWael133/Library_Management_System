package com.example.Library_Management_System.BooksServiceTest;

import com.example.Library_Management_System.Repository.BookRepository;
import com.example.Library_Management_System.Service.BookService;
import com.example.Library_Management_System.Service.BookServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.cache.CacheManager;

public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    CacheManager cacheManager;
    @InjectMocks
    BookService bookService = new BookServiceImpl(cacheManager, bookRepository);
}
