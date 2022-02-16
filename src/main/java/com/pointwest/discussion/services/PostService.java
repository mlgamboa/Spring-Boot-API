package com.pointwest.discussion.services;

import com.pointwest.discussion.models.Post;

public interface PostService {
    void createPost(Post post);

    Iterable<Post> getPosts();
}
