package com.moviebook.repository;

import com.moviebook.model.Filme;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FilmeRepository extends MongoRepository< Filme, Long> {


    Filme findByName(String titulo);

    void deleteByTitle(String titulo);
}

