package com.moviebook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Filme {

    @Id
    private Long id;

    private String refTmdb;

    private String title;
    private String genero;
    private String descricao;
    private String status;

}
