package com.example.Library_Management_System.PatronServiceTest;

import com.example.Library_Management_System.Repository.PatronRepository;
import com.example.Library_Management_System.Service.PatronService;
import com.example.Library_Management_System.Service.PatronServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.cache.CacheManager;

public class PatronServiceTest {

    @Mock
    PatronRepository patronRepository;
    @Mock
    CacheManager cacheManager;
    @InjectMocks
    PatronService patronService = new PatronServiceImpl(cacheManager, patronRepository);
}
