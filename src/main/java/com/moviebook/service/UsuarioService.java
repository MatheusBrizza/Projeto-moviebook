package com.moviebook.service;

import com.moviebook.model.Filme;
import com.moviebook.model.Usuario;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private FilmeRepository filmeRepository;

    public UsuarioService (UsuarioRepository usuarioRepository, FilmeRepository filmeRepository){
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
    }

    public Usuario findUsuarioById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public Filme findFilmeById(Long idFilme) {
        return filmeRepository.findById(idFilme).get();
    }

    public Usuario findByName(String nome, UsuarioRepository usuarioRepository) {
        return usuarioRepository.findByNome(nome);
    }
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public void deleteByName(String nome, UsuarioRepository usuarioRepository) {
        usuarioRepository.deleteByNome(nome);
    }

    public Usuario addInWatchList(Long idUsuario, Long idFilme ){
        Usuario usuarioBuscado = findUsuarioById(idUsuario);
        Filme filmeBuscado = findFilmeById(idFilme);
        List<Filme> iWantToWatch= usuarioBuscado.getIWantToWatch();
        iWantToWatch.add(filmeBuscado);
        return usuarioRepository.save(usuarioBuscado);
    }

    public void removerFilmeDaWatchListByUsuario(Long idUsuario, Long idFilme) {
        // Algo tipo: logService.criarLog(idUsuario, idFilme)
        // TODO Implementar código para remover filme idFile da Watch List do Usuário idUsuario
    }


//    public boolean iExists(Usuario usuario) {
//        return false;
//    }
}
