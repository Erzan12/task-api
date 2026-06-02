package com.erzan.task_api.controller;

import com.erzan.task_api.dto.BuyProductRequest;
import com.erzan.task_api.dto.UserRequest;
import com.erzan.task_api.dto.api_response.ApiResponse;
import com.erzan.task_api.entity.User;
import com.erzan.task_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public List<User> getAllUserLists() {
//        return userService.getAllUserLists();
//    }

//    @GetMapping
//    public List<User> search(
//            @RequestParam(required = false) String name
//    ) {
//        return userService.search(name);
//    }

    @GetMapping
    public Page<User> search(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return userService.search(name, pageable);
    }

    @GetMapping("/{id}")
    public  User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PostMapping
//    public User createUser(@Valid @RequestBody UserRequest request) {
//        return userService.createUser(request);
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserRequest request) {

        User saveUser = userService.createUser(request);

        ApiResponse<User> response =
                new ApiResponse<>(
                        true,
                        "User created successfully",
                        saveUser
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "User has been deleted";
    }
}
