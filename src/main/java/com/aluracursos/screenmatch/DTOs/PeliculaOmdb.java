package com.aluracursos.screenmatch.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PeliculaOmdb(
        @JsonAlias("Title") String tituloPelicula,
        @JsonAlias("Year") String anioLanzamiento,
        @JsonAlias("Released") String fechaLanzamiento,
        @JsonAlias("Runtime") String duracion,
        @JsonAlias("Plot") String descripcion,
        @JsonAlias("Type") String tipoContenido
) {
}
