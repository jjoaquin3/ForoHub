package com.jjoaquin3.forohub.model;

import com.jjoaquin3.forohub.model.dto.TopicRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "TOPIC")
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 512)
    private String title;

    @Column(name = "message", columnDefinition = "text", nullable = false)
    private String message;

    @Column(name = "status", nullable = false, length = 64)
    private String status;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    /*
    @Column(name = "answers", nullable = true)
    private Integer countReplies;
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reply> replies;

    public void setData(TopicRequestDTO request, User user, Course course)
    {
        this.title = request.title();
        this.message = request.message();
        this.status = request.status();
        this.creationDate = LocalDateTime.now();
        this.user = user;
        this.course = course;
        this.replies = new HashSet<>();
    }

    public void setDataUpdate(TopicRequestDTO request, User user, Course course)
    {
        this.title = request.title();
        this.message = request.message();
        this.status = request.status();
        //this.creationDate = LocalDateTime.now();
        this.user = user;
        this.course = course;
        //this.replies = new HashSet<>();
    }
}
