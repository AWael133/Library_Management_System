package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Entity.User;
import com.example.Library_Management_System.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id,
                                             @RequestBody @Valid User user){
        return ResponseEntity.ok(userService.editUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
