package com.jjoaquin3.forohub.service.impl;

import com.jjoaquin3.forohub.model.Course;
import com.jjoaquin3.forohub.model.Topic;
import com.jjoaquin3.forohub.model.User;
import com.jjoaquin3.forohub.model.dto.TopicRequestDTO;
import com.jjoaquin3.forohub.model.dto.TopicResponseDTO;
import com.jjoaquin3.forohub.repository.ICourseRepository;
import com.jjoaquin3.forohub.repository.ITopicRepository;
import com.jjoaquin3.forohub.repository.IUserRepository;
import com.jjoaquin3.forohub.service.ITopicService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService implements ITopicService
{
    private final ITopicRepository topicRepository;
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;

    @Autowired
    public TopicService(ITopicRepository topicRepository, IUserRepository userRepository, ICourseRepository courseRepository)
    {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @Override
    public TopicResponseDTO save(TopicRequestDTO entity)
    {
        //Validar que no exista
        if (topicRepository.existsByTitle(entity.title()))
            throw new EntityExistsException("Topic con nombre: " + entity.title() + " ya existe");

        Optional<User> userOptional = userRepository.findById(entity.user());
        if (userOptional.isEmpty()) throw new ValidationException("Usuario con ID " + entity.user() + " no existe");

        Optional<Course> courseOptional = courseRepository.findById(entity.course());
        if (courseOptional.isEmpty()) throw new ValidationException("Course con ID " + entity.user() + " no existe");

        Topic topico = new Topic();
        topico.setData(entity, userOptional.get(), courseOptional.get());
        topico = topicRepository.save(topico);

        return new TopicResponseDTO(topico);
    }

    @Transactional
    @Override
    public TopicResponseDTO update(Long id, TopicRequestDTO entity)
    {
        //Validar que exista el topi
        Optional<Topic> topicoOptional = topicRepository.findById(id);
        if (topicoOptional.isEmpty()) throw new EntityNotFoundException("Topic con ID: " + id + " no existe");

        //Validar que exista el course
        Optional<Course> courseOptional = courseRepository.findById(entity.course());
        if (courseOptional.isEmpty())
            throw new EntityNotFoundException("Course con ID: " + entity.course() + " no existe");

        //Validar que exista el usuario
        Optional<User> userOptional = userRepository.findById(entity.user());
        if (userOptional.isEmpty()) throw new EntityNotFoundException("User con ID: " + entity.user() + " no existe");

        Topic topico = topicoOptional.get();
        topico.setDataUpdate(entity,userOptional.get(), courseOptional.get());

        topico = topicRepository.save(topico);
        return new TopicResponseDTO(topico);
    }

    @Transactional
    @Override
    public void delete(Long id)
    {
        var topicOptional = topicRepository.findById(id);
        if (topicOptional.isPresent()) topicRepository.delete(topicOptional.get());
        else throw new EntityNotFoundException("Topic con ID " + id + " no existe");
    }

    @Override
    public TopicResponseDTO findById(Long id)
    {
        return topicRepository.findById(id).map(TopicResponseDTO::new).orElse(null);
    }

    @Override
    public Page<TopicResponseDTO> findAll(Pageable pageable)
    {
        return topicRepository.findAll(pageable).map(TopicResponseDTO::new);
    }
}
