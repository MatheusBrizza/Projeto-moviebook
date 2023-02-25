package com.moviebook.service;

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

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final LogService logService;
    private final TMDBIntegrationService tmdbIntegrationService;

    public UsuarioService(UsuarioRepository usuarioRepository, FilmeRepository filmeRepository,
                          LogService logService, TMDBIntegrationService tmdbIntegrationService) {
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
        this.logService = logService;
        this.tmdbIntegrationService = tmdbIntegrationService;
    }

    public Usuario encontrarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

/*    public Filme encontrarFilmePorId(Long idFilme) {
        return filmeRepository.findById(idFilme).get();
    }
*/

    public List<FilmeTmdbDTO> encontrarFilmesAssistidosPorUsuarioId(Long idUsuario) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<FilmeTmdbDTO> listaDeFilmesAssistidos = usuario.getAssistidos();

        return listaDeFilmesAssistidos;
    }

    public List<FilmeTmdbDTO> encontrarFilmesQueroAssistirPorUsuarioId(Long idUsuario) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<FilmeTmdbDTO> listaFilmesQueroAssistir = usuario.getQueroAssistir();

        return listaFilmesQueroAssistir;
    }

    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarFilmeListaQueroAssistir(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<FilmeTmdbDTO> listaQueroAssistirDoUsuario = usuario.getQueroAssistir();
        FilmeTmdbDTO filmeBuscado = tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
 //       Filme filme = encontrarFilmePorId(usuarioRequestDTO.getIdFilme());
        listaQueroAssistirDoUsuario.add(filmeBuscado);
        usuario.setQueroAssistir(listaQueroAssistirDoUsuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarFilmeListaAssistidos(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<FilmeTmdbDTO> listaDeFilmesAssistidos = usuario.getAssistidos();
        FilmeTmdbDTO filmeBuscado = tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
//        Filme filme = encontrarFilmePorId(usuarioRequestDTO.getIdFilme());
        listaDeFilmesAssistidos.add(filmeBuscado);
        usuario.setAssistidos(listaDeFilmesAssistidos);

        return  usuarioRepository.save(usuario);
    }

    public void removerFilmeListaQueroAssistirPorUsuario(Long idUsuario, Long idFilme) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<FilmeTmdbDTO> listaFilmesQueroAssistir = usuario.getQueroAssistir();
        FilmeTmdbDTO filme = tmdbIntegrationService.encontrarPorId(idFilme);
        listaFilmesQueroAssistir.remove(filme);
        System.out.println(listaFilmesQueroAssistir);
        usuario.setQueroAssistir(listaFilmesQueroAssistir);
        usuarioRepository.save(usuario);
        Log logOperacao = Log.builder()
                .data(LocalDateTime.now())
                .usuario(usuario.getLogin())
                .operacao(Operacao.DELETAR)
                .filme(filme.getTitle())
                .build();

        logService.criarLog(logOperacao);
    }

}
