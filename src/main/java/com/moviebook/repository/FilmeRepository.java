package com.moviebook.repository;

import com.moviebook.model.Filme;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FilmeRepository extends MongoRepository<Filme, Long> {
    Filme findByNome(String nome);

    void deleteByNome(String titulo);

    Optional<Filme> findById(Long id);

    Filme findByGenero(String genero);
}
