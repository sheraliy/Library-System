package com.sherbek.Library.System.user.controller;

import com.sherbek.Library.System.user.model.User;
import com.sherbek.Library.System.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User addUser(
            @Valid @RequestBody User user
    ) {
        return userService.save(user);
    }
}