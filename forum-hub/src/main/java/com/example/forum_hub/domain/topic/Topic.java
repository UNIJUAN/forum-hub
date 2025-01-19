package com.example.forum_hub.domain.topic;

import com.example.forum_hub.domain.enums.TopicStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    private String author;
    private String course;
    private Boolean active;

    public Topic() {}

    public Topic(String title, String message, String author, String course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;
        this.creationDate = LocalDateTime.now();
        this.status = TopicStatus.NOT_ANSWERED;
        this.active = true;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }

    public Boolean getActive() {
        return active;
    }

    // Methods
    public void update(String title, String message) {
        if (title != null) {
            this.title = title;
        }
        if (message != null) {
            this.message = message;
        }
    }

    public void delete() {
        this.active = false;
    }
}