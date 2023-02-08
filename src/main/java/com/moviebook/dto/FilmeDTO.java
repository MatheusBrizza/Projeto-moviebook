package com.moviebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FilmeDTO {

    private int id;
    private String nome;
    private String descricao;
    private String status;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private double voteCount;

}