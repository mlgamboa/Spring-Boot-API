package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    // Create
    ResponseEntity createPost(String stringToken, Post post);
    // Retrieve
    Iterable<Post> getPosts();
    // Delete
    ResponseEntity deletePost(Long id, String stringToken);
    // Update
    ResponseEntity updatePost(Long id, Post post, String stringToken);
    // Archive
    ResponseEntity archivePost(Long id);
    // Retrieve active post
    ResponseEntity getActivePosts();
    // Retrieving posts of certain users
    Iterable<Post> getMyPosts(String stringToken);




}
