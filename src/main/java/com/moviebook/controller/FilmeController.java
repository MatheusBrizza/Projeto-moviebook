package com.moviebook.controller;

import com.moviebook.dto.FilmeRequestDTO;
import com.moviebook.model.Filme;
import com.moviebook.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> criar(@RequestBody FilmeRequestDTO filmeRequestDTO){
        Filme filmeCriado = filmeService.criar(filmeRequestDTO);
        return new ResponseEntity<>(filmeCriado, HttpStatus.CREATED);
    }

}