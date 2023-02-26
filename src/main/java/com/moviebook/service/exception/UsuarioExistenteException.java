package com.moviebook.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioExistenteException extends ResponseStatusException {

    public UsuarioExistenteException(HttpStatus status, String message) {
        super(status, message);
    }
}
