package com.example.forum_hub.dto.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicCreateDTO(
        @NotBlank(message = "El título es obligatorio")
        String title,

        @NotBlank(message = "El mensaje es obligatorio")
        String message,

        @NotBlank(message = "El autor es obligatorio")
        String author,

        @NotBlank(message = "El curso es obligatorio")
        String course
) {}