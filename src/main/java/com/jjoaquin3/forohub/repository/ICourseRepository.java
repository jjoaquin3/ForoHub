package com.jjoaquin3.forohub.repository;

import com.jjoaquin3.forohub.model.Course;
import com.jjoaquin3.forohub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long>
{
}
