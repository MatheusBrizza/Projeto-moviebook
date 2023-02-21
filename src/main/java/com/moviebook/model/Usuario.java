package com.moviebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Usuario extends Pessoa {

    @Id
    private Long id;
    private String login;
    private String senha;
    private Boolean isAtivo;
    private List<Filme> queroAssistir = new ArrayList<>();
    private List<Filme> assistidos = new ArrayList<>();

}
