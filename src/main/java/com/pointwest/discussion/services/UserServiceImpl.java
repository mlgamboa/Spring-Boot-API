package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;
import com.pointwest.discussion.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    public ResponseEntity deleteUser(Long id){
        userRepositories.deleteById(id);
        return new ResponseEntity("User was successfully deleted", HttpStatus.OK);
    }

    public ResponseEntity updateUser(Long id, User user){
       User userUpdate = userRepositories.findById(id).get();
       userUpdate.setUsername(user.getUsername());
       userUpdate.setPassword(user.getPassword());
       userRepositories.save(userUpdate);
       return new ResponseEntity("Update successful", HttpStatus.OK);

    }

    // Find by username
    public Optional<User> findByUsername(String username){
        // If the findByUsername method returns null, it will throw a NullPointerException
        // Using the .ofNullable method will avoid this from happening
        return Optional.ofNullable(userRepositories.findByUsername(username));
    }


}
