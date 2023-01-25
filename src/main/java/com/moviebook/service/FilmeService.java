package com.moviebook.service;

import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    private FilmeRepository filmeRepository;

    public FilmeService (FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }
    public Filme findById(FilmeRepository filmeRepository, Long id) {
        return filmeRepository.findById(id).get();
    }

    public Filme findByTitle(String titulo, FilmeRepository filmeRepository) {
        return filmeRepository.findByName(titulo);
    }
    public Filme create(Filme filme){
        return filmeRepository.save(filme);
    }

    public void deleteById(Long id) {
        filmeRepository.deleteById(id);
    }
    public void deleteByTitle(String titulo, FilmeRepository filmeRepository) {
        filmeRepository.deleteByTitle(titulo);
    }
//    public boolean existsInTheWatchList(Filme filme) {
//        return false;
//    }

}
