package com.pointwest.discussion.services;

import com.pointwest.discussion.config.JwtToken;
import com.pointwest.discussion.models.Post;
import com.pointwest.discussion.models.User;
import com.pointwest.discussion.repositories.PostRepositories;
import com.pointwest.discussion.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Defined that the class is a service that will contain business logic for the application
@Service
public class PostServiceImpl implements  PostService{

    // An obj cannot be instantiated from interfaces
    // @autowired annotation allows us to use the interface as if it was an instance of an obj
    // Allow us to use the methods from the CrudRepository
    @Autowired
    private PostRepositories postRepository;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    JwtToken jwtToken;

    // Create posts
    public ResponseEntity createPost(String stringToken,Post post){
        String message = "Post created successfully";
        HttpStatus status = HttpStatus.CREATED;
        // ArrayList<String> duplicates = new ArrayList<>();
        boolean isDuplicate = false;

        // get all posts and create a loop to check all records
       for(Post indivPost: postRepository.findAll()){
           // compare if the title is the same with records in the DB
           if(post.getTitle().equalsIgnoreCase(indivPost.getTitle())){
               message = "Duplicate post found";
               status = HttpStatus.CONFLICT;
               // duplicates.add(indivPost.getTitle())
               isDuplicate = true;
           }
       }
       // if (duplicates.size <= 0){
        // postRepository.save(post);
        // }

        if (!isDuplicate){
            User author = userRepositories.findByUsername(jwtToken.getUsernameFromToken(stringToken));
            Post newPost = new Post();
            newPost.setTitle(post.getTitle());
            newPost.setContent(post.getContent());
            newPost.setUser(author);
            postRepository.save(newPost);
        }
        return new ResponseEntity(message, status);
    }


    // Get posts
    public Iterable<Post> getPosts(){
        return postRepository.findAll();
    }


    // Delete posts
    public ResponseEntity deletePost(Long id, String stringToken){
        Post postForUpdating = postRepository.findById(id).get();
        String postAuthorName = postForUpdating.getUser().getUsername();
        String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

        if(authenticatedUsername.equalsIgnoreCase(postAuthorName)){
            postRepository.deleteById(id);
            return new ResponseEntity("Post deleted successfully", HttpStatus.OK);

        } else {
            return new ResponseEntity("You are not authorized to delete this post", HttpStatus.UNAUTHORIZED);
        }

    }

    // Update posts
    public ResponseEntity updatePost(Long id, Post post, String stringToken){
        Post postForUpdating = postRepository.findById(id).get();
        String postAuthorName = postForUpdating.getUser().getUsername();
        String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

        if(authenticatedUsername.equalsIgnoreCase(postAuthorName)){
            postForUpdating.setContent(post.getContent());
            postRepository.save(postForUpdating);
            return new ResponseEntity("Post updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity("You are not authorized to edit this post", HttpStatus.UNAUTHORIZED);
        }
    }


    // Archive post
    public ResponseEntity archivePost(Long id){
        // Look for the record using the id
        Post postForArchive= postRepository.findById(id).get();
        // Updating the active attribute
        postForArchive.setActive(!postForArchive.isActive());
        // Save the record
        postRepository.save(postForArchive);
        return new ResponseEntity("Post was update to "+postForArchive.isActive(), HttpStatus.OK);
    }



    // Get active posts
    public ResponseEntity getActivePosts(){
        ArrayList<Post> activePosts = new ArrayList<>();
        for (Post post: postRepository.findAll()){
            if(post.isActive()){
                activePosts.add(post);
            }
        }
        return new ResponseEntity(activePosts, HttpStatus.OK);
    }



    // Retrieving posts of certain users
    public Iterable<Post> getMyPosts(String stringToken){
        User author = userRepositories.findByUsername(jwtToken.getUsernameFromToken(stringToken));
        return author.getPosts();
    }

}
