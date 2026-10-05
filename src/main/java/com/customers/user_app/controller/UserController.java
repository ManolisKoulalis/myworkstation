package com.customers.user_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customers.user_app.dto.UserRequest;
import com.customers.user_app.dto.UserResponse;
import com.customers.user_app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
   
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<UserResponse> users = userService.getUserList();

        return ResponseEntity.ok(users);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

    	UserResponse user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }
    
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {

    	UserResponse createdUser = userService.saveUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable Long id, @Valid @RequestBody UserRequest request) {

    	UserResponse updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(updatedUser);
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchBySurname(@RequestParam String surname) {

        List<UserResponse> users =userService.getUsersBySurname(surname);

        return ResponseEntity.ok(users);
    }
    
    
}
