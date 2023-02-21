package com.moviebook.service;

import com.moviebook.dto.FilmeRequestDTO;
import com.moviebook.dto.FilmeTmdbDTO;
import com.moviebook.dto.UsuarioRequestDTO;
import com.moviebook.model.Filme;
import com.moviebook.model.Log;
import com.moviebook.model.Usuario;
import com.moviebook.model.enums.Operacao;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final FilmeRepository filmeRepository;

    private final LogService logService;

    private final TMDBIntegrationService tmdbIntegrationService;

    public UsuarioService(UsuarioRepository usuarioRepository, FilmeRepository filmeRepository, LogService logService, TMDBIntegrationService tmdbIntegrationService) {
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
        this.logService = logService;
        this.tmdbIntegrationService = tmdbIntegrationService;
    }

    public Usuario encontrarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public Filme encontrarFilmePorId(Long idFilme) {
        return filmeRepository.findById(idFilme).get();
    }

    public Usuario encontrarPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletarPorId(Long idUsuario) {usuarioRepository.deleteById(idUsuario);}

    public void deletarPorNome(String nome) {
        usuarioRepository.deleteByNome(nome);
    }

    public Usuario adicionarFilmeListaQueroAssistir(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());

        List<Filme> listaQueroAssistirDoUsuario = usuario.getQueroAssistir();

        // TODO Verificar se filme a ser adicionado já está na lista listaQueroAssistirDoUsuario
     //   tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
        // Buscar e adicionar filme ns lista de Quero Assistir do Usuário
        Filme filme = encontrarFilmePorId(usuarioRequestDTO.getIdFilme());
        listaQueroAssistirDoUsuario.add(filme);
        usuario.setQueroAssistir(listaQueroAssistirDoUsuario);

        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarFilmeListaAssistidos(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<Filme> listaDeFilmesAssistidos = usuario.getAssistidos();
        Filme filme = encontrarFilmePorId(usuarioRequestDTO.getIdFilme());
        listaDeFilmesAssistidos.add(filme);
        usuario.setAssistidos(listaDeFilmesAssistidos);

        return  usuarioRepository.save(usuario);
    }

    //public List<>

    public void removerFilmeListaQueroAssistirPorUsuario(Long idUsuario, Long idFilme) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<Filme> listaFilmesQueroAssistir = usuario.getQueroAssistir();
        Filme filme = encontrarFilmePorId(idFilme);
        listaFilmesQueroAssistir.remove(filme);
        usuario.setQueroAssistir(listaFilmesQueroAssistir);
        usuarioRepository.save(usuario);

        Log logOperacao = Log.builder()
                .data(LocalDateTime.now())
                .usuario(usuario.getLogin())
                .operacao(Operacao.DELETAR)
                .filme(filme.getNome())
                .build();

        logService.criarLog(logOperacao);
    }

    public List<Filme> encontrarFilmesAssistidosPorUsuarioId(Long idUsuario) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<Filme> listaDeFilmesAssistidos = usuario.getAssistidos();

        // TODO Está retornando os Filmes Assistidos que estão no Banco de Dados
        // Porém, devemos buscar o resto das informações (informações comepletas) na API TMDB
        // Dica: usar o serviço TMDBIntegrationService (já injetado)
        return listaDeFilmesAssistidos;
    }

    private void validarFilmeExisteListaQueroAssistir(Usuario usuario, Filme filme) {

    }
}
