package com.moviebook.controller;

import com.moviebook.model.Usuario;
import com.moviebook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

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

    UsuarioService usuarioService;
    public  UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @GetMapping("/{id}")
    private ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {
        Usuario responseUsuario = usuarioService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuario);
    }
    @PostMapping
    private ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario responseUsusario = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUsusario);
    }

    @PostMapping("/{idUsuario}/{idFilme}")
    private ResponseEntity<Usuario> adicionarNaListaDeAssistidos(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idFilme") Integer idFilme) {
        Usuario responseUsuario = usuarioService.addInWatchedList(idUsuario, idFilme);
        return ResponseEntity.status(HttpStatus.OK).body(responseUsuario);

    }

}
