package com.moviebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class FilmeTmdbDTO {

    private Long id;
    private String title;
    private String overview;

}