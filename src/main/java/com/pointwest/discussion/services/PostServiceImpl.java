package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.repositories.PostRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Defined that the class is a service that will contain business logic for the application
@Service
public class PostServiceImpl implements  PostService{

    // An obj cannot be instantiated from interfaces
    // @autowired annotation allows us to use the interface as if it was an instance of an obj
    // Allow us to use the methods from the CrudRepository
    @Autowired
    private PostRepositories postRepository;

    // Create posts
    public void createPost(Post post){
        postRepository.save(post);
    }

    // Get posts
    public Iterable<Post> getPosts(){
        return postRepository.findAll();
    }

    // Delete posts
    public ResponseEntity deletePost(Long id){
        postRepository.deleteById(id);
        return new ResponseEntity("Post deleted successfully", HttpStatus.OK);
    }

    // Update posts
    public ResponseEntity updatePost(Long id, Post post){
        Post postForUpdating = postRepository.findById(id).get();
        postForUpdating.setTitle(post.getTitle());
        postForUpdating.setContent(post.getContent());
        postRepository.save(postForUpdating);
        return new ResponseEntity("Post updated successfully", HttpStatus.OK);
    }

}
