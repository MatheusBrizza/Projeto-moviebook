package com.moviebook.controller;

import com.moviebook.dto.FilmeDTO;
import com.moviebook.service.TMDBIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MoviebookController {

    private TMDBIntegrationService tmdbIntegrationService;

    public MoviebookController(TMDBIntegrationService tmdbIntegrationService) {
        this.tmdbIntegrationService = tmdbIntegrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.tmdbIntegrationService.findById(id));
    }

}