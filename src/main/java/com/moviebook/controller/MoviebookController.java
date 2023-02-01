package com.moviebook.controller;

import com.moviebook.dto.MovieDTO;
import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import com.moviebook.service.FilmeService;
import com.moviebook.service.TMDBIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MoviebookController {

    //    private TMDBIntegrationService tmdbIntegrationService;
    private FilmeService filmeService;

    //TMDBIntegrationService tmdbIntegrationService
    public MoviebookController(FilmeService filmeService) {
//        this.tmdbIntegrationService = tmdbIntegrationService;
        this.filmeService = filmeService;
    }

    /*    @GetMapping("/{id}")
        public ResponseEntity<MovieDTO> buscarPorId(@PathVariable("id") int id) {
            return ResponseEntity.ok(this.tmdbIntegrationService.buscarPorId(id));
        }*/
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> buscarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.filmeService.buscarPorId(id));
    }

    /*    @GetMapping("/nome/{nome}")
        public ResponseEntity<MovieDTO> buscarPorTitulo(@PathVariable("nome") String nome) {
            return ResponseEntity.ok(this.tmdbIntegrationService.buscarPorTitulo(nome));
        }*/
    @GetMapping("/nome/{nome}")
    public ResponseEntity<MovieDTO> buscarPorTitulo(@PathVariable("title") String title) {
        return ResponseEntity.ok(this.filmeService.buscarPorTitulo(title));
    }
    @GetMapping("/genero/{genero}")
        public ResponseEntity<MovieDTO> buscarPorGenero(@PathVariable("genero") String genero) {
            return ResponseEntity.ok(this.filmeService.buscarPorGenero(genero));
        }
/*    @PostMapping("/filme/{}")
    public ResponseEntity<Filme> criar(Filme filme){
        Filme filmeCriado = filmeService.criar(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }
    @PutMapping("/{id}")
    ResponseEntity<Filme> alterar(@PathVariable("id") Long id, Filme filme){
        Filme filmeAlterado = filmeService.update(id, filme);
        return ResponseEntity.ok(filme);
    }*/

}