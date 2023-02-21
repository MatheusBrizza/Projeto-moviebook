package com.moviebook.model;

import com.moviebook.model.enums.Operacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Log {

    private LocalDateTime data;
    private Operacao operacao;
    private String usuario;
    private String filme;

}
