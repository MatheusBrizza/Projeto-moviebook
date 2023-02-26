package com.moviebook.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioNaoExistenteException extends ResponseStatusException {

    public UsuarioNaoExistenteException(HttpStatus status, String message) {
        super(status, message);
    }
}
