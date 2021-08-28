package com.bhupendra.karki.rediswithspringboot.repository;

import com.bhupendra.karki.rediswithspringboot.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface UserRepository {

    void save(User user) throws JsonProcessingException;
    Map<String, User> findAll();
    User findById(String id);
    void update(String id, User user) throws JsonProcessingException;
    void delete(String id);
}
