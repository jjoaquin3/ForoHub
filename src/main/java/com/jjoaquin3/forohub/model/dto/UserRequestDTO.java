package com.jjoaquin3.forohub.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjoaquin3.forohub.model.Role;
import com.jjoaquin3.forohub.model.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDTO
{
    private String username;

    private String password;

    private String email;

    private String role;
}
