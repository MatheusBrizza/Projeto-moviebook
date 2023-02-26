package com.moviebook.controller.exception;

public class FilmeNaoEncontradoException extends RuntimeException{
    public FilmeNaoEncontradoException(){
        super("Filme não encontrado");
    }
}


