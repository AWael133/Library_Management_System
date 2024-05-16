package com.example.Library_Management_System.UserServiceTst;

import com.example.Library_Management_System.Repository.UserRepository;
import com.example.Library_Management_System.Service.UserService;
import com.example.Library_Management_System.Service.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//    @Mock
    CacheManager cacheManager = new SimpleCacheManager();
    @InjectMocks
    UserService userService = new UserServiceImpl(cacheManager, passwordEncoder, userRepository);
}
