package com.moviebook.controller;

import com.moviebook.dto.UsuarioRequestDTO;
import com.moviebook.model.Filme;
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
    public ResponseEntity<List<Filme>> encontrarFilmesAssistidosUsuarioPorId(@PathVariable("idUsuario") Long idUsuario) {
        List<Filme> listaDeFilmesAssistidos = usuarioService.encontrarFilmesAssistidosPorUsuarioId(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(listaDeFilmesAssistidos);
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


/*
    private UsuarioService service;

    public UsuarioController( UsuarioService service) {
        this.service = service;
    }

    @GetMapping("{/id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = service.findById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> createUsuario(@ResponseBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

*/

}
