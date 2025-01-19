package com.example.forum_hub.controller;

import com.example.forum_hub.dto.topic.TopicCreateDTO;
import com.example.forum_hub.dto.topic.TopicResponseDTO;
import com.example.forum_hub.dto.topic.TopicUpdateDTO;
import com.example.forum_hub.domain.topic.Topic;
import com.example.forum_hub.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository repository;

    // Constructor injection instead of @Autowired
    public TopicController(TopicRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> create(
            @RequestBody @Valid TopicCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        var topic = new Topic(data.title(), data.message(), data.author(), data.course());
        repository.save(topic);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicResponseDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> list(
            @PageableDefault(size = 10, sort = {"creationDate"}) Pageable pagination
    ) {
        var page = repository.findAllByActiveTrue(pagination).map(TopicResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> detail(@PathVariable Long id) {
        var topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> update(@RequestBody @Valid TopicUpdateDTO data) {
        var topic = repository.getReferenceById(data.id());
        topic.update(data.title(), data.message());
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var topic = repository.getReferenceById(id);
        topic.delete();
        return ResponseEntity.noContent().build();
    }
}