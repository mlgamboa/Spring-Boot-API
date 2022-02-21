package com.pointwest.discussion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    @Column
    private String password;

    // Annotating the posts property, establishes the relationship to the user model
    @OneToMany(mappedBy = "user")
    // prevent the posts field to be printed out
    // posts attribute will not be included during serialization/deserialization
    @JsonIgnore
    // set class is a collection that contains no duplicate elements
    private Set<Post> posts;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
