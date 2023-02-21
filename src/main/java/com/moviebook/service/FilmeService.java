package com.moviebook.service;

import com.moviebook.dto.FilmeRequestDTO;
import com.moviebook.model.Filme;
import com.moviebook.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    private TMDBIntegrationService tmdbIntegrationService;

    public FilmeService (FilmeRepository filmeRepository){
        this.tmdbIntegrationService = tmdbIntegrationService;
        this.filmeRepository = filmeRepository;
    }
    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id).get();
    }

    public Filme buscarPorNome(String nome) {
        return filmeRepository.findByNome(nome);
    }

    public Filme buscarPorGenero(String genero){
        return filmeRepository.findByGenero(genero);
    }

    public Filme criar(FilmeRequestDTO filmeRequestDTO) {
        Filme filmeParaSalvar = Filme.builder()
                .id(filmeRequestDTO.getId())
                .nome(filmeRequestDTO.getNome())
                .build();

        return filmeRepository.save(filmeParaSalvar);
    }

    public Filme update(Filme filme, String nome) throws Exception {
        Filme filmeUpdate = this.filmeRepository.findByNome(nome);
        if (filmeUpdate == null) {
            //throw new BusinessExceptions(HttpStatus.BAD_REQUEST, "Não é possivel atualizar este filme.");
            throw new Exception("Filme não encontrado");
        }

        filmeUpdate.setNome(filme.getNome());

        return filmeRepository.save(filme);
    }

    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    public void deletarPorId(Long id) {
        filmeRepository.deleteById(id);
    }
    public void deletarPorTitulo(String titulo) {
        filmeRepository.deleteByNome(titulo);
    }
//    public boolean existsInTheWatchList(Filme filme) {
//        return false;
//    }

}
