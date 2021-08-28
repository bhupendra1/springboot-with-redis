package com.bhupendra.karki.rediswithspringboot.repository;

import com.bhupendra.karki.rediswithspringboot.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations;
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User: " + objectMapper.writeValueAsString(user));
        redisTemplate.opsForHash().put("USER", user.getId(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public User findById(String id) {
        return (User) hashOperations.get("USER", id);
    }

    @Override
    public void update(String id, User user) throws JsonProcessingException {
        User userInRedis = findById(id);
        if (userInRedis != null) {
            userInRedis.setName(user.getName());
            userInRedis.setSalary(user.getSalary());
            save(userInRedis);
        } else {
            save(user);
        }
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("USER", id);
    }
}
