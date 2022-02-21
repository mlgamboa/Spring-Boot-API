package com.pointwest.discussion.models;

import javax.persistence.*;

// Entity annotation marks the Java Class as a representation of an entity from the database table "posts"
// Post class represents a single table in mySql
@Entity
// Table annotation designates the table name related to the model
@Table(name="posts")
public class Post {

    // Properties
    // Id annotation indicates that this property represents the primary key of the table
    @Id
    // values for the id property will be auto-incremented
    @GeneratedValue
    private Long id;

    // Column annotation are class properties that represent table columns in a relation database
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private boolean active = true;

    // Annotating the user property, establishes the relationship to the post model
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors
    public Post() {
    }

    public Post(String title, String content, boolean active) {
        this.title = title;
        this.content = content;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
