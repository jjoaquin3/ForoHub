package com.jjoaquin3.forohub.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjoaquin3.forohub.model.Course;
import com.jjoaquin3.forohub.model.Reply;
import com.jjoaquin3.forohub.model.Topic;
import com.jjoaquin3.forohub.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicResponseDTO
{
    private Long id;

    private String title;

    private String message;

    private String status;

    private LocalDateTime creationDate;
    /*
    @Column(name = "answers", nullable = true)
    private Integer countReplies;
    */

    private String user;

    private String course;

    public TopicResponseDTO(Topic entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.message = entity.getMessage();
        this.status = entity.getStatus();
        this.creationDate = entity.getCreationDate();
        this.user = entity.getUser().getUsername();
        this.course = entity.getCourse().getName();
    }
}
