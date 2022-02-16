package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;

public interface UserService {
    void createUser (User user);
    Iterable<User> getUsers();
}
