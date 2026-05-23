package com.erzan.task_api.controller;

import com.erzan.task_api.entity.User;
import com.erzan.task_api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUserLists() {
        return userService.getAllUserLists();
    }

    @GetMapping("/{id}")
    public  User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
