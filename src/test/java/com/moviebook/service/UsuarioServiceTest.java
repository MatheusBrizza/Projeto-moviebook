package com.moviebook.service;

import com.moviebook.dto.FilmeTmdbDTO;
import com.moviebook.dto.UsuarioRequestDTO;
import com.moviebook.model.Filme;
import com.moviebook.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    @Mock
    UsuarioService usuarioService;
    @Mock
    Usuario usuario;

    @Test
    public void criarUsuario() {

        //Criando um usurio para teste
        Usuario usuario = new Usuario(1L, "João", "senha123", true, null, null);

        //Chama o método a ser testado
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);

        assertNotNull(usuarioCriado);
        assertNotNull("João", usuarioCriado.getLogin());
        assertNotNull("senha123", usuarioCriado.getSenha());

    }

    @Test
    public void criarUsuarioLancandoExeceaoQuandoNomeInvalido() {
        Usuario usuario = new Usuario(1L, null, "senha123", true, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.criarUsuario(Usuario.builder()
                    .id(1L)
                    .login(null)
                    .senha("senha123")
                    .isAtivo(true)
                    .queroAssistir(null)
                    .assistidos(null)
                    .build());
        });
    }

    @Test
    public void criarUsuarioLancandoExeceaoQuandoSenhaInvalida() {
        Usuario usuario = new Usuario(1L, "Melissa", null, true, null, null);

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.criarUsuario(usuario);
        });
    }

    @BeforeEach
    public void setUp() {
        usuario = new Usuario(1L, "João", "123456", true, null, null);
    }

    @Test
    public void adicionarFilmeListaAssistidos_deveAdicionarFilmeNaLista() {
        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana.");
        usuarioService.adicionarFilmeListaAssistidos(usuarioDTO);
        Assertions.assertTrue(usuario.getAssistidos().contains(filme));
    }

    @Test
    public void adicionarFilmeListaAssistidosDeveLancarExceptionSeUsuarioForNulo() {
        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();
        Filme filme = new Filme(11L, "Star Wars", "Ficção científica", null);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> usuarioService.adicionarFilmeListaAssistidos(usuarioDTO));
    }

    @Test
    public void adicionarFilmeListaAssistidosDeveLancarExceptionSeFilmeForNulo() {
        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> usuarioService.adicionarFilmeListaAssistidos(usuarioDTO));
    }

    @Test
    public void testAdicionarFilmeListaQueroAssistir() {
        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();
        Filme filme = new Filme(11L, "Star Wars", "Ficção científica", null);
        usuarioService.adicionarFilmeListaQueroAssistir(usuarioDTO);

        assertTrue(usuario.getQueroAssistir().contains(filme));
    }

    //Teste para o método encontrarUsuarioPorId().
    @Test
    public void testeEncontrarUsuarioPorId() {
        //Cria objetos necessários para o teste.
        Long id = usuarioService.criarUsuario(usuario).getId();

        //Chama o metodo que será testado
        Usuario usuarioEncontrado = usuarioService.encontrarUsuarioPorId(id);

        //Verifica o resultado
        assertNotNull(usuarioEncontrado);
        assertEquals("João", usuarioEncontrado.getLogin());
        assertEquals("123", usuarioEncontrado.getSenha());
    }
    //Este teste cria um objeto UsuarioService, adiciona um usuario usando o metodo criar e depois procura esse usuario
    //usando o metodo encontrarUsuarioPorId. Em seguida verfica se o usuário encontrado tem as mesmas propriedades que o original adicionado.

    @Test
    public void testEncontrarFilmesAssisitdosPorUsuarioId() {

        //Cria os objetos necessários.
        usuarioService.criarUsuario(usuario);
        Long idFilme = usuario.getId();
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana.");
        usuarioService.encontrarFilmesAssistidosPorUsuarioId(idFilme);

        //Chama o metodo que será testado
        List<FilmeTmdbDTO> filmeAssistidos = usuarioService.encontrarFilmesAssistidosPorUsuarioId(idFilme);


        // Verifica o resultado
        assertEquals(1L, filmeAssistidos.size());
        assertEquals("O poderoso Chefão", filmeAssistidos.get(0).getTitle());
        assertEquals("A historia da familia Corleone, uma das mais famosas familas da mafia americana.", filmeAssistidos.get(0).getOverview());
    }

    @Test
    public void testEncontrarFilmesQueroAssistirPorUsuarioId(){

        //Cria os objetos necessários.
        usuarioService.criarUsuario(usuario);
        Long idFilme = usuario.getId();
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana.");
        usuarioService.encontrarFilmesQueroAssistirPorUsuarioId(idFilme);

        //Chama o metodo que será testado
        List<FilmeTmdbDTO> filmeQueroAssistir = usuarioService.encontrarFilmesQueroAssistirPorUsuarioId(idFilme);


        // Verifica o resultado
        assertEquals(1L, filmeQueroAssistir.size());
        assertEquals("O poderoso Chefão", filmeQueroAssistir.get(0).getTitle());
        assertEquals("A historia da familia Corleone, uma das mais famosas familas da mafia americana.", filmeQueroAssistir.get(0).getOverview());
    }

    @Test
    public void testRemoverFilmeListaQueroAssistirPorUsuario() {
        Filme filme = new Filme(1L, "O poderoso Chefão", "Drama", "A historia da familia Corleone, uma das mais famosas familas da mafia americana.");
        // Removendo o filme da lista de filmes a assistir do usuário
        usuarioService.removerFilmeListaQueroAssistirPorUsuario(usuario.getId(), filme.getId());

        // Buscando o usuário no banco de dados para verificar se o filme foi removido da lista
        Usuario usuarioAtualizado = usuarioService.encontrarUsuarioPorId(usuario.getId());

        // Verificando se o filme foi removido da lista de filmes a assistir do usuário
        assertFalse(usuarioAtualizado.getQueroAssistir().contains(filme));
    }



}


