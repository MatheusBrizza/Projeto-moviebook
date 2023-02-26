package com.moviebook.service;

import com.moviebook.dto.FilmeRequestDTO;
import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import org.springframework.stereotype.Service;


@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    public FilmeService (FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }

    public Filme criar(FilmeRequestDTO filmeRequestDTO) {
        Filme filmeParaSalvar = Filme.builder()
                .id(filmeRequestDTO.getId())
                .nome(filmeRequestDTO.getNome())
                .build();

        return filmeRepository.save(filmeParaSalvar);
    }

}
