package com.moviebook.controller;

import com.moviebook.dto.FilmeTmdbDTO;
import com.moviebook.dto.UsuarioRequestDTO;
import com.moviebook.model.Usuario;
import com.moviebook.service.FilmeService;
import com.moviebook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    UsuarioService usuarioService;

    FilmeService filmeService;

    public UsuarioController(UsuarioService usuarioService, FilmeService filmeService){
        this.usuarioService = usuarioService;
        this.filmeService = filmeService;
    }

    @GetMapping("/{idUsuario}/filmes")
    public ResponseEntity<List<FilmeTmdbDTO>> encontrarFilmesAssistidosUsuarioPorId(@PathVariable("idUsuario") Long idUsuario) {
        List<FilmeTmdbDTO> listaDeFilmesAssistidos = usuarioService.encontrarFilmesAssistidosPorUsuarioId(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(listaDeFilmesAssistidos);
    }

    @GetMapping("/{idUsuario}/filmes/quero-assistir")
    public ResponseEntity<List<FilmeTmdbDTO>> encontrarFilmesListaQueroAssitirPorId(@PathVariable("idUsuario") Long idUsuario) {
        List<FilmeTmdbDTO> listaQueroAssistir = usuarioService.encontrarFilmesQueroAssistirPorUsuarioId(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(listaQueroAssistir);
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario responseUsusario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUsusario);
    }

    @PostMapping("/filmes/quero-assistir")
    public ResponseEntity<Usuario> adicionarNaListaDeAssistidos(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario responseUsuario = usuarioService.adicionarFilmeListaQueroAssistir(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUsuario);
    }
    @PostMapping("/filmes/assistidos")
    public ResponseEntity<Usuario> filmesAssistidos(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        Usuario responseUsuario = usuarioService.adicionarFilmeListaAssistidos(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUsuario);
    }

    @DeleteMapping("/{idUsuario}/filmes/{idFilme}/quero-assistir")
    public ResponseEntity<Void> deletarFilmeListaQueroAssistir(@PathVariable("idUsuario") Long idUsuario,
                                                               @PathVariable("idFilme") Long idFilme) {
        usuarioService.removerFilmeListaQueroAssistirPorUsuario(idUsuario, idFilme);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
