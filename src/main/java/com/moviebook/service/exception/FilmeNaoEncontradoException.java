package com.moviebook.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FilmeNaoEncontradoException extends ResponseStatusException {

    public FilmeNaoEncontradoException(HttpStatus status, String message) {
        super(status, message);
    }
}
