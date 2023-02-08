package com.moviebook.repository;

import com.moviebook.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {


    Usuario findByNome(String nome);

    void deleteByNome(String nome);
}
