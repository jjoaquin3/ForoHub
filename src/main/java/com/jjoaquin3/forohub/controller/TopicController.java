package com.jjoaquin3.forohub.controller;

import com.jjoaquin3.forohub.model.dto.TopicRequestDTO;
import com.jjoaquin3.forohub.model.dto.TopicResponseDTO;
import com.jjoaquin3.forohub.service.ITopicService;
import com.jjoaquin3.forohub.service.impl.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController
{
    private final ITopicService topicService;

    @Autowired
    public TopicController(ITopicService iTopicService)
    {
        this.topicService = iTopicService;
    }

    //01. Registro de un nuevo topico
    @PostMapping
    public ResponseEntity<TopicResponseDTO> save(@RequestBody @Valid TopicRequestDTO request)
    {
        var topic = topicService.save(request);
        var uri = UriComponentsBuilder.fromPath("/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    //02. Mostrar todos los tópicos
    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> findAll(Pageable pageable)
    {
        return ResponseEntity.ok(topicService.findAll(pageable));
    }

    //03. Mostrar topic por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopic(@PathVariable Long id)
    {
        var topic = topicService.findById(id);
        if (topic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(topic);
    }

    //04. Actualizar topic
    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> update(@PathVariable Long id, @RequestBody TopicRequestDTO entity)
    {
        var topic = topicService.update(id, entity);
        if (topic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(topic);
    }

    //05. Eliminar topic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
