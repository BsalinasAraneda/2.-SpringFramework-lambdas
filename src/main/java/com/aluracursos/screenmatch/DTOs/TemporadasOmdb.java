package com.aluracursos.screenmatch.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record TemporadasOmdb(
        @JsonAlias("Season") Integer numeroTemporada,
        // Corresponde al mapeo que se generara al momento de obtener la respuesta
        // Esto se realizara mediante la implementación del ObjectMapper()
        @JsonAlias("Episodes") List<EpisodioOmdb> episodios) {
}
