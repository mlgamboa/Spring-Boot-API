package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    void createUser (User user);
    Iterable<User> getUsers();
    ResponseEntity deleteUser(Long id);
    ResponseEntity updateUser(Long id, User user);
    // Optional - defines if the method may/may not return an object of the User Class
    Optional<User> findByUsername(String Username);
}
