package com.pointwest.discussion.repositories;

import com.pointwest.discussion.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// By extending CrudRepository, the PostRepository will inherit its pre-defined methods for creaing, retrieving, updating and deleting records
@Repository
// Post is the data type of the data to be accepted as arguments
// Object data type of the data returned from the database
public interface PostRepositories extends CrudRepository<Post, Object> {
}
