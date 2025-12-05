package com.aluracursos.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    //ObjectMapper es una clase correspondiente al paquete jackson.databind
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param json ==> Corresponde al objeto que retorna la API OMDB
     * @param clase ==> Clase que recibe por parametro y sera mapeada Pelicula-Serie-etc
     * @return
     * @param <T> ==> Clase genera que asocia desde el Record SerieOmdb-DatosPelicula-etc
     */
    @Override
    public <T> T otbenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
