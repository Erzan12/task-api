package com.erzan.task_api.controller;

import com.erzan.task_api.dto.UserRequest;
import com.erzan.task_api.dto.api_response.ApiResponse;
import com.erzan.task_api.dto.api_response.list_response.OrderResponse;
import com.erzan.task_api.dto.api_response.list_response.ProductResponse;
import com.erzan.task_api.dto.api_response.list_response.user_list_response.UserOrderResponse;
import com.erzan.task_api.dto.api_response.list_response.user_list_response.UserResponse;
import com.erzan.task_api.entity.User;
import com.erzan.task_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

//    @GetMapping
//    public Page<User> search(
//            @RequestParam(required = false) String name,
//            Pageable pageable
//    ) {
//        return userService.search(name, pageable);
//    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {

        List<UserResponse> responses = userService.getUsers()
                .stream()
                .map(user -> {

                    List<ProductResponse> products = user.getProductsForSale()
                            .stream()
                            .map(product -> new ProductResponse(
                                    product.getId(),
                                    product.getName(),
                                    product.getColor(),
                                    product.getExpiry(),
                                    product.getSeller().getName(),
                                    product.getCreatedAt()
                            ))
                            .toList();

                    List<UserOrderResponse> orders = user.getOrders()
                            .stream()
                            .map(order -> new UserOrderResponse(
                                    order.getId(),
                                    order.getSeller().getName(),
                                    order.getProduct().getName(),
                                    order.getCreatedAt()
                            ))
                            .toList();

                    return new UserResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getAge(),
                            user.getAddress(),
                            products,
                            orders,
                            user.getCreatedAt(),
                            user.getUpdatedAt()
                    );
                })
                .toList();

        ApiResponse<List<UserResponse>> response =
                new ApiResponse<>(
                        true,
                        "Users fetched successfully",
                        responses
                );

        return ResponseEntity.ok(response);
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
