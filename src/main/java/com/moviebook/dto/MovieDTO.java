package com.moviebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieDTO {

    private int id;
    private String title;
    private String genre;
    private String overview;
    private String status;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private double voteCount;

}