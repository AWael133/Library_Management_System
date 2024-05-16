package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.UserRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final CacheManager cacheManager;

    String model = "User";
    final String USERINFO_CACHE = "userInfo";
    final String USER_DETAILS_CACHE = "userDetails";

    private UserRepository userRepository;

    public UserServiceImpl(CacheManager cacheManager,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository){
        this.cacheManager = cacheManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value=USERINFO_CACHE, key="#id")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
    }

    @Override
    public User addUser(User user) {
        user.setId(null);
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("username already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        Cache userInfoCache = cacheManager.getCache(USERINFO_CACHE);
        Cache userDetailsCache = cacheManager.getCache(USER_DETAILS_CACHE);
        if(userInfoCache != null)
            userInfoCache.put(user.getId(), user);
        if(userDetailsCache != null)
            userDetailsCache.put(user.getUsername(), user);
        return user;
    }

    @Override
    @CachePut(value=USERINFO_CACHE, key="#id")
    public User editUser(Long id, User user) {
        User user0 = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        user.setId(user0.getId());

        if(userRepository.existsByUsernameAndIdNot(user.getUsername(), user0.getId()))
            throw new RuntimeException("username already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));

        Cache userInfoCache = cacheManager.getCache(USERINFO_CACHE);
        Cache userDetailsCache = cacheManager.getCache(USER_DETAILS_CACHE);
        if(userInfoCache != null)
            userInfoCache.evictIfPresent(id);
        if(userDetailsCache != null)
            userDetailsCache.evictIfPresent(user.getUsername());
        userRepository.delete(user);
    }
    @Override
    @Cacheable(value=USER_DETAILS_CACHE, key="#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(model));
    }
}
