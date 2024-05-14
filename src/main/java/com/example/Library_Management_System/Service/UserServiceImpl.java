package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Entity.Patron;
import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Exceptions.EntityNotFoundException;
import com.example.Library_Management_System.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    String model = "User";

    private UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
    }

    @Override
    public User addUser(User user) {
        user.setId(null);
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("username already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User editUser(Long id, User user) {
        User user0 = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        user.setId(user0.getId());

        if(userRepository.existsByUsernameAndIdNot(user.getUsername(), user0.getId()))
            throw new RuntimeException("username already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User patron = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(model));
        userRepository.delete(patron);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(model));
    }
}
