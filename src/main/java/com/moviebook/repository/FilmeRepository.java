package com.moviebook.repository;

import com.moviebook.dto.MovieDTO;
import com.moviebook.model.Filme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmeRepository extends MongoRepository<Filme, Long> {
    Filme findByTitulo(String titulo);

    void deleteByTitulo(String titulo);

    MovieDTO buscarPorTitulo(String title);

    MovieDTO buscarPorId(Long id);

    MovieDTO buscarPorGenero(String genero);
}
