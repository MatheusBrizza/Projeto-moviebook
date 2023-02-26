package com.moviebook.controller.exception;

public class UsuarioNaoEncontradoException extends  RuntimeException{
    public UsuarioNaoEncontradoException(){
        super("Usuario não encontrado!");
    }
}