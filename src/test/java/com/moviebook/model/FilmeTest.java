package com.moviebook.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

//Neste teste estamos importando a classe Filme e criando uma classe de teste de unidade chamada FilmeTest.
//Em cada método de teste, estamos criando um objeto Filme que será usado no teste.
//Em cada teste, estamos verificando se o objeto Filme tem o atributo correto e se ele correspnde ao valor esperado.
//No ultimo teste, estamos verificando se o método toMap() retorna uma mapa com as chaves e valores corretos.
// Ao ser executado a saída deve indicar que todos passaram com sucesso.
//É preciso importa a classe HashMap e a interface Map.


public class FilmeTest {
    @Test
    public void testFilmeTemId(){
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana.");
        assertEquals(1, filme.getId());
    }

    @Test
    public void testFilmeTemNome(){
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana." );
        assertEquals("O poderoso Chefão", filme.getNome());
    }
    @Test
    public void testFilmeTemGenero(){
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana." );
        assertEquals("Drama", filme.getGenero());
    }
    @Test
    public void testFilmeTemDescricao(){
        Filme filme = new Filme(2L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana." );
        assertEquals("A historia da familia Corleone, uma das mais famosas familas da mafia americana.", filme.getDescricao());
    }

}

