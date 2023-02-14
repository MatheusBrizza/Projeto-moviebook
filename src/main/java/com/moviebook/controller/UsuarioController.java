package com.moviebook.controller;

import com.moviebook.model.Usuario;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.service.FilmeService;
import com.moviebook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    UsuarioService usuarioService;

    FilmeService filmeService;

    public  UsuarioController(UsuarioService usuarioService, FilmeService filmeService){
        this.usuarioService = usuarioService;
        this.filmeService = filmeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
        Usuario responseUsuario = usuarioService.findUsuarioById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario responseUsusario = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUsusario);
    }

    @PostMapping("/{idUsuario}/{idFilme}")
    public ResponseEntity<Usuario> adicionarNaListaDeAssistidos(@PathVariable("idUsuario") Long idUsuario,
                                                                @PathVariable("idFilme") Long idFilme) {
        Usuario responseUsuario = usuarioService.addInWatchList(idUsuario, idFilme);
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuario);
    }

    @DeleteMapping("/{idUsuario}/filmes/{idFilme}/watch-list")
    public ResponseEntity<Void> deletarFilmeDaWatchListByUsuario(@PathVariable("idUsuario") Long idUsuario,
                                                                 @PathVariable("idFilme") Long idFilme) {
        System.out.println(idUsuario);
        System.out.println(idFilme);

        usuarioService.removerFilmeDaWatchListByUsuario(idUsuario, idFilme);
        return null;
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
