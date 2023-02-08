package com.moviebook.service;

import com.moviebook.dto.FilmeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TMDBIntegrationService {

    private RestTemplate restTemplate;

    @Value("${tmdb-external-api}")
    private String uri;

    @Value("${tmdb-api-key}")
    private String apiKey;

    public TMDBIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public FilmeDTO findById(int id) {
        String url = generateURLIntegration(id);
        return this.restTemplate.getForObject(url, FilmeDTO.class);
    }

    private String generateURLIntegration(int id) {
        return this.uri + "/movie/" + id + "?api_key=" + this.apiKey ;
    }

}
