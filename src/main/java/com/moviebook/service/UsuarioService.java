package com.moviebook.service;

import com.moviebook.model.Filme;
import com.moviebook.model.Usuario;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService (Usuario usuario){
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario findByName(String nome, UsuarioRepository usuarioRepository) {
        return usuarioRepository.findByName(nome);
    }
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    public void deleteByName(String nome, UsuarioRepository usuarioRepository) {
        usuarioRepository.deleteByName(nome);
    }

//    public boolean iExists(Usuario usuario) {
//        return false;
//    }
}
