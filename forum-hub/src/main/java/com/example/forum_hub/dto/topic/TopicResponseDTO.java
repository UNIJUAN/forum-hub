package com.example.forum_hub.dto.topic;

import com.example.forum_hub.domain.enums.TopicStatus;
import com.example.forum_hub.domain.topic.Topic;
import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        TopicStatus status,
        String author,
        String course
) {
    public TopicResponseDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor(),
                topic.getCourse()
        );
    }
}