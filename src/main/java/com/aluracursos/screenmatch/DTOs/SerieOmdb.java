package com.aluracursos.screenmatch.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Propiedad que permite ignorar todos los campos que no son mapeados desde la serie
//Por defecto viene en false, de esta forma el ObjectMapper trae los campos que no son mapeados y genera un error
@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * @JsonAlias y @JsonProperty son anotaciones de Jackson, una biblioteca Java para procesar JSON, que
 * ayudan a mapear propiedades de clase a campos JSON.
 * @JsonAlias = Permite unicamente leer los datos
 * @JsonProperty = Permite tanto la lectura como escritura de datos
 * @param titulo
 * @param totalTemporadas
 * @param evaluacion
 */

public record SerieOmdb(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("imdbRating")  String evaluacion
) {
}
