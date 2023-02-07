package com.moviebook.service;

import com.moviebook.dto.MovieDTO;
import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

//    private TMDBIntegrationService tmdbIntegrationService;

    public FilmeService (FilmeRepository filmeRepository){
//        this.tmdbIntegrationService = tmdbIntegrationService;
        this.filmeRepository = filmeRepository;
    }
    public MovieDTO buscarPorId(Long id) {
        return filmeRepository.buscarPorId(id);
    }

    public MovieDTO buscarPorTitulo(String title) {
        return filmeRepository.buscarPorTitulo(title);
    }
    public MovieDTO buscarPorGenero(String genero){
        return filmeRepository.buscarPorGenero(genero);
    }
    public Filme criar(Filme filme){
        return filmeRepository.save(filme);
    }

    public Filme update(Filme filme, String nome) throws Exception {
        Filme filmeUpdate = this.filmeRepository.findByTitulo(nome);
        if (filmeUpdate == null) {
            //throw new BusinessExceptions(HttpStatus.BAD_REQUEST, "Não é possivel atualizar este filme.");
            throw new Exception("Filme não encontrado");
        }

        filmeUpdate.setNome(filme.getNome());

        return filmeRepository.save(filme);
    }

    public void deletarPorId(Long id) {
        filmeRepository.deleteById(id);
    }
    public void deletarPorTitulo(String titulo, FilmeRepository filmeRepository) {
        filmeRepository.deleteByTitulo(titulo);
    }
//    public boolean existsInTheWatchList(Filme filme) {
//        return false;
//    }

}
