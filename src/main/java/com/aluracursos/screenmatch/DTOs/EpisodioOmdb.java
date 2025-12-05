package com.aluracursos.screenmatch.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Desactivando el mapeo de propiedades no definidas
@JsonIgnoreProperties(ignoreUnknown = true)

public record EpisodioOmdb(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer numeroEpisodio,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Released") String fechaDeLanzamiento
) {
}
