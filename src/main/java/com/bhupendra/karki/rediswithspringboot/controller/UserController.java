package com.bhupendra.karki.rediswithspringboot.controller;

import com.bhupendra.karki.rediswithspringboot.models.User;
import com.bhupendra.karki.rediswithspringboot.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void save(@RequestBody User user) throws JsonProcessingException {
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @GetMapping
    public Map<String, User> retrieveUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.delete(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id,
                           @RequestBody User user) throws JsonProcessingException {
        userRepository.update(id, user);
    }

}
