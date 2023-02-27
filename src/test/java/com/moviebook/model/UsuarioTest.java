package com.moviebook.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UsuarioTest {

    @Test
    public void testGetLogin(){
        Usuario usuario = new Usuario(1L, "João", "123", true, null, null);
        assertEquals("João", usuario.getLogin());
    }
    @Test
    public void testGetSenha(){
        Usuario usuario = new Usuario(2L, "Maria", "456", true, null, null);
        assertEquals("456", usuario.getSenha());
    }

    @Test
    public void testSetLogin(){
        Usuario usuario = new Usuario(3L, "Pedro", "789", true, null, null);
        usuario.setLogin("jose");
        assertEquals("jose", usuario.getLogin());
    }
    @Test
    public  void testSetSenha(){
        Usuario usuario = new Usuario(4L, "Ana", "abc", true, null, null);
        usuario.setSenha("def");
        assertEquals("def", usuario.getSenha());
    }
}
