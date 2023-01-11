package com.moviebook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filme {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;


    List<Filme>queroAssitir;

    List<Filme>assistidos;


}
