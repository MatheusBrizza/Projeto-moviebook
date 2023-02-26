package com.moviebook.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UsuarioControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity<String> handleApiError(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Erro desconhecido";

        if (ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            status = HttpStatus.BAD_REQUEST;
            message = String.format("Filme %s não encontrado");
        } else if (ex instanceof HttpServerErrorException) {
            HttpServerErrorException httpServerErrorException = (HttpServerErrorException) ex;
            status = httpServerErrorException.getStatusCode();
            message = httpServerErrorException.getResponseBodyAsString();
        }

        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleConnectionError(ResourceAccessException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Erro de conexão";

        return new ResponseEntity<>(message, status);
    }}
