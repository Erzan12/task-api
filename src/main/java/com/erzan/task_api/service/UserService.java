package com.erzan.task_api.service;

import com.erzan.task_api.entity.User;
import com.erzan.task_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUserLists() {
        return userRepository.findAll();
    }
}
