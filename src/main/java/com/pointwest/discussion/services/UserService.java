package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void createUser (User user);
    Iterable<User> getUsers();
    ResponseEntity deleteUser(Long id);
    ResponseEntity updateUser(Long id, User user);
}
