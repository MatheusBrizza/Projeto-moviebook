package com.moviebook.controller;

import com.moviebook.dto.FilmeDTO;
import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.service.FilmeService;
import com.moviebook.service.TMDBIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MoviebookController {

    //    private TMDBIntegrationService tmdbIntegrationService; FALTA PASSAR NO PARAMETRO DO CONSTRUTOR
    private final FilmeRepository filmeRepository;
    private final FilmeService filmeService;

    public MoviebookController(FilmeRepository filmeRepository, FilmeService filmeService) {
//        this.tmdbIntegrationService = tmdbIntegrationService;
        this.filmeRepository = filmeRepository;
        this.filmeService = filmeService;
    }
    @GetMapping("/{idFilme}")
    public ResponseEntity<Optional<Filme>> findById(@PathVariable("id") Long idFilme){
        return ResponseEntity.ok(this.filmeRepository.findById(idFilme));
    }
    @GetMapping("/findByName/{nome}")
    public ResponseEntity<Filme> findByNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok(this.filmeRepository.findByNome(nome));
    }
    @GetMapping("/findByGenre/{genero}")
    public ResponseEntity <Filme> findByGenero(@PathVariable String genero){
        return ResponseEntity.ok(this.filmeRepository.findByGenero(genero));
    }
    @PostMapping
    public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme){
        Filme filmeCriado = filmeService.criar(filme);
        return new ResponseEntity<>(filmeCriado, HttpStatus.CREATED);
    }
    @PutMapping("/{idFilme}")
    public ResponseEntity<Filme> atualizar(@PathVariable Filme idFilme, @RequestBody String filme) throws Exception {
        Filme filmeAtualizado = this.filmeService.update(idFilme,filme);
        return ResponseEntity.ok(filmeAtualizado);
    }

    @DeleteMapping("/{idFilme}")
    public ResponseEntity<Filme> deletarFilmePorId(@PathVariable Long idFilme){
        System.out.println(idFilme);
        filmeService.deletarPorId(idFilme);
        return null;
    }

    @DeleteMapping("/deletarPorTitulo/{nome}") // ADICIONAR
    public ResponseEntity<Filme> deletarFilmePorTitulo(@PathVariable Long idFilme, String nome){
        System.out.println(nome);
        filmeService.deletarPorTitulo(nome);
        return null;
    }


  /*  private TMDBIntegrationService tmdbIntegrationService;

    public MoviebookController(TMDBIntegrationService tmdbIntegrationService) {
        this.tmdbIntegrationService = tmdbIntegrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.tmdbIntegrationService.findById(id));
    }*/


}