package com.moviebook.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Filme {

    @Id
    private Long id;
    private String nome;
    private String genero;
    private String descricao;

}
