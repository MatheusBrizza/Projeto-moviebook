package com.moviebook.model;

import com.moviebook.model.enums.Operacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Log {

    @Id
    private Long id;
    private LocalDateTime data;
    private Operacao operacao;
    private Usuario usuario;
    private Filme filme;
}
