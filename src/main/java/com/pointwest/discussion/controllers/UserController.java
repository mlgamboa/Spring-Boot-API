package com.pointwest.discussion.controllers;


import com.pointwest.discussion.models.User;
import com.pointwest.discussion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    // Create user
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

    // Get all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

}
