package com.moviebook.model;

import lombok.Data;

import java.time.LocalDate;


public abstract class Pessoa {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
}
