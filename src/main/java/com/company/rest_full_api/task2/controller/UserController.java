package com.company.rest_full_api.task2.controller;

import com.company.rest_full_api.task1.payload.ApiResponse;
import com.company.rest_full_api.task2.entity.User;
import com.company.rest_full_api.task2.payload.UserDto;
import com.company.rest_full_api.task2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        return userService.getUser();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editUser(@Valid @PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUser(id,userDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

}
