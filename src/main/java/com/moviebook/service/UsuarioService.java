package com.moviebook.service;

import com.moviebook.dto.FilmeTmdbDTO;
import com.moviebook.dto.UsuarioRequestDTO;
import com.moviebook.model.Log;
import com.moviebook.model.Usuario;
import com.moviebook.model.enums.Operacao;
import com.moviebook.repository.UsuarioRepository;
import com.moviebook.service.exception.FilmeJaAdicionadoException;
import com.moviebook.service.exception.UsuarioExistenteException;
import com.moviebook.service.exception.UsuarioNaoExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LogService logService;
    private final TMDBIntegrationService tmdbIntegrationService;

    public UsuarioService(UsuarioRepository usuarioRepository, LogService logService,
                          TMDBIntegrationService tmdbIntegrationService) {
        this.usuarioRepository = usuarioRepository;
        this.logService = logService;
        this.tmdbIntegrationService = tmdbIntegrationService;
    }

    public Usuario encontrarUsuarioPorId(Long idUsuario) {
        validarUsuarioNaoExistentePorId(idUsuario);
        return usuarioRepository.findById(idUsuario).get();
    }


    public List<FilmeTmdbDTO> encontrarFilmesAssistidosPorUsuarioId(Long idUsuario) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        return usuario.getAssistidos();
    }

    public List<FilmeTmdbDTO> encontrarFilmesQueroAssistirPorUsuarioId(Long idUsuario) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        return usuario.getQueroAssistir();
    }

    public Usuario criarUsuario(Usuario usuario) throws UsuarioExistenteException {
        validarUsuarioExistentePorId(usuario.getId());
        usuario = Usuario.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .isAtivo(usuario.getIsAtivo())
                .queroAssistir(usuario.getQueroAssistir())
                .assistidos(usuario.getAssistidos())
                .build();

        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarFilmeListaQueroAssistir(UsuarioRequestDTO usuarioRequestDTO) throws FilmeJaAdicionadoException {
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<FilmeTmdbDTO> listaFilmes = usuario.getQueroAssistir();
        FilmeTmdbDTO filmeBuscado = tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
        if (listaFilmes.contains(filmeBuscado)) {
            throw new FilmeJaAdicionadoException(HttpStatus.BAD_REQUEST,
                    String.format("O filme %s já está na lista", filmeBuscado.getTitle()));
        }
        listaFilmes.add(filmeBuscado);
        usuario.setQueroAssistir(listaFilmes);

        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarFilmeListaAssistidos(UsuarioRequestDTO usuarioRequestDTO) throws FilmeJaAdicionadoException {
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<FilmeTmdbDTO> listaFilmes = usuario.getAssistidos();
        FilmeTmdbDTO filmeBuscado = tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
        if (listaFilmes.contains(filmeBuscado)) {
            throw new FilmeJaAdicionadoException(HttpStatus.BAD_REQUEST,
                    String.format("O filme %s já está na lista", filmeBuscado.getTitle()));
        }
        listaFilmes.add(filmeBuscado);
        usuario.setAssistidos(listaFilmes);

        return usuarioRepository.save(usuario);
    }

    public void removerFilmeListaQueroAssistirPorUsuario(Long idUsuario, Long idFilme) {
        Usuario usuario = encontrarUsuarioPorId(idUsuario);
        List<FilmeTmdbDTO> listaFilmesQueroAssistir = usuario.getQueroAssistir();
        FilmeTmdbDTO filme = tmdbIntegrationService.encontrarPorId(idFilme);
        listaFilmesQueroAssistir.remove(filme);
        usuario.setQueroAssistir(listaFilmesQueroAssistir);
        usuarioRepository.save(usuario);
        logOperacao(usuario, filme);
    }

    public void logOperacao(Usuario usuario, FilmeTmdbDTO filme) {
        Log logOperacao = Log.builder()
                .data(LocalDateTime.now())
                .usuario(usuario.getLogin())
                .operacao(Operacao.DELETAR)
                .filme(filme.getTitle())
                .build();

        logService.criarLog(logOperacao);
    }

/*    public void validarFilmeLista(UsuarioRequestDTO usuarioRequestDTO) throws FilmeJaAdicionadoException {
        Usuario usuario = encontrarUsuarioPorId(usuarioRequestDTO.getIdUsuario());
        List<FilmeTmdbDTO> listaFilmes = usuario.getAssistidos();
        FilmeTmdbDTO filmeBuscado = tmdbIntegrationService.encontrarPorId(usuarioRequestDTO.getIdFilme());
        if (listaFilmes.contains(filmeBuscado)) {
                throw new FilmeJaAdicionadoException(HttpStatus.BAD_REQUEST,
                        String.format("O filme %s já adicionado na lista", filmeBuscado.getTitle()));
        }
        listaFilmes.add(filmeBuscado);
    }
*/

    public void validarUsuarioNaoExistentePorId(Long idUsuario) throws UsuarioNaoExistenteException {
        Optional<Usuario> usuarioExists = usuarioRepository.findById(idUsuario);

        if (usuarioExists.isEmpty()) {
            throw new UsuarioNaoExistenteException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Não existe um usuario com id %s, é preciso criar antes de completar esta ação.", idUsuario)
            );
        }
    }
    public void validarUsuarioExistentePorId(Long idUsuario) throws UsuarioExistenteException{
        Optional<Usuario> usuarioExists = usuarioRepository.findById(idUsuario);

        if (!usuarioExists.isEmpty()) {
            throw new UsuarioExistenteException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Usuario com id %s já existe.", idUsuario)
            );
        }
    }
}
