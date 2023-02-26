package com.moviebook.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FilmeJaAdicionadoException extends ResponseStatusException {

    public FilmeJaAdicionadoException(HttpStatus status, String message) {
        super(status, message);
    }
}
