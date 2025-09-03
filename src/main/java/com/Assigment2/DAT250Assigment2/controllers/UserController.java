package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager pollManager;

    @GetMapping
    public List<User> getAllUsers() {
        return pollManager.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return pollManager.createUser(user);
    }
}
