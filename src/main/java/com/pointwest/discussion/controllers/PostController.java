package com.pointwest.discussion.controllers;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Serializes all responses of the PostController as HTTP responses
// Serialization - An obj in Java can be represented as a sequence of bytes that includes the obj's data and the types of data store in the obj
@RestController
// @crossorigin enables cross origin requests
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    // Create a post
    @RequestMapping(value="/posts", method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(
           @RequestHeader(value = "Authorization") String stringToken, @RequestBody Post post){
        return postService.createPost(stringToken, post);
    }


    // Get all posts
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<Object> getPosts(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }



    // Delete post id
    @RequestMapping(value = "/posts/{postid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Long postid, @RequestHeader(value = "Authorization") String stringToken){
        return postService.deletePost(postid, stringToken);
    }



    // Update post id
    @RequestMapping(value = "/posts/{postid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePost(@PathVariable Long postid, @RequestBody Post post, @RequestHeader(value = "Authorization") String stringToken){
        return postService.updatePost(postid, post, stringToken);
    }


    // Archive
    @RequestMapping(value = "/posts/archive/{postid}",method = RequestMethod.PUT)
    public ResponseEntity archivePost (@PathVariable Long postid){
        return postService.archivePost(postid);
    }



    // Get active posts
    @RequestMapping(value = "/activePosts", method = RequestMethod.GET)
    public ResponseEntity getActivePosts(){
        return postService.getActivePosts();
    }


    // Get posts of certain users
    @RequestMapping(value = "/myPosts", method = RequestMethod.GET)
    public ResponseEntity<Object> getMyPosts(@RequestHeader(value = "Authorization") String stringToken){
        return new ResponseEntity<>(postService.getMyPosts(stringToken), HttpStatus.OK);
    }
}
