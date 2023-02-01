package com.moviebook.controller;

import com.moviebook.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

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


}
