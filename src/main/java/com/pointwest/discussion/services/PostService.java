package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    // Create
    void createPost(Post post);
    // Retrieve
    Iterable<Post> getPosts();
    // Delete
    ResponseEntity deletePost(Long id);
    // Update
    ResponseEntity updatePost(Long id, Post post);

}
