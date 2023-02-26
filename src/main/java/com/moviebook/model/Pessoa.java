package com.moviebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
}
