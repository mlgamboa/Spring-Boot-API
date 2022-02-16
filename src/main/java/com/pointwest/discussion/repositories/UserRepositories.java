package com.pointwest.discussion.repositories;

import com.pointwest.discussion.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends CrudRepository<User, Object> {

}
