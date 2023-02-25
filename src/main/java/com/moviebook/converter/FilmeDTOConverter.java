package com.moviebook.converter;

import com.moviebook.dto.FilmeTmdbDTO;
import com.moviebook.model.Filme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilmeDTOConverter {

    public static FilmeTmdbDTO converterParaDTO(Filme filme) {
        return FilmeTmdbDTO.builder()
                .id(filme.getId())
                .title(filme.getNome())
                .build();
    }

}