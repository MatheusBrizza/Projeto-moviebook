package com.moviebook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Usuario extends Pessoa {

    @Id
    private Long id;

    private String login;

    private String password;

    private Boolean isAtivo;

    private List< Filme> iWantTowatch;

    private List< Filme> assisted;
}
