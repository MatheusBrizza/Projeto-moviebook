package com.moviebook.controller;

import com.moviebook.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController( UsuarioService service) {
        this.service = service;
    }

    private ResponseEntity<Usuario> findById(@PathVariable)
}
