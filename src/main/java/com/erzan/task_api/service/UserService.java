package com.erzan.task_api.service;

import com.erzan.task_api.dto.UserRequest;
import com.erzan.task_api.entity.Product;
import com.erzan.task_api.entity.User;
import com.erzan.task_api.repository.ProductRepository;
import com.erzan.task_api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

//    public List<User> getAllUserLists() {
//        return userRepository.findAll();
//    }

//    public List<User> search(String name) {
//
//        //if no search param, return all users
//        if (name == null || name.isBlank()) {
//            return userRepository.findAll();
//        }
//
//        //otherwise search by name
//        return userRepository.findByNameContainingIgnoreCase(name);
//    }

    public Page<User> search(String name, Pageable pageable) {

        // no search
        if (name == null || name.isBlank()) {
            return userRepository.findAll(pageable);
        }

        // search with pagination
        return userRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found or does not exist"));
    }

    public User createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .address(request.getAddress())
                .build();

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    public User updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName((request.getName()));
        user.setAge((request.getAge()));
        user.setEmail((request.getEmail()));
        user.setAddress((request.getAddress()));

        return userRepository.save(user);
    }

    public User buyProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // assign to user
        product.setUser(user);

        // save updated product
        productRepository.save(product);

        return user;
    }


}
