package com.jjoaquin3.forohub.repository;

import com.jjoaquin3.forohub.model.Topic;
import com.jjoaquin3.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>
{
}
