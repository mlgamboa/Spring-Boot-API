package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;
import com.pointwest.discussion.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepositories userRepositories;

    public void createUser(User user) {
        userRepositories.save(user);
    }

    public Iterable<User> getUsers(){
        return userRepositories.findAll();
    }
}
