package com.jjoaquin3.forohub.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicRequestDTO(

        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotBlank
        String status,

        /*Seria el id de usuario pero yo lo guardo asi con el nombre de la tabla c:*/
        @NotNull
        Long user,

        /*Seria el id de course pero yo lo guardo asi con el nombre de la tabla c:*/
        @NotNull
        Long course
) {}
