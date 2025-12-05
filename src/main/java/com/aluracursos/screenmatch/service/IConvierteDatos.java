package com.aluracursos.screenmatch.service;

public interface IConvierteDatos {
    /**
     * Metodo que permite trabajar con tipos de datos genericos.
     * Para este caso en particular la API retorna Series-Temporadas, Peliculas, etc
     * @param json ==> Corresponde al objeto que retorna la API OMDB
     * @param "Class<T>":Parametro que hace referencia a la clase generica de <T>, lo que este permite
     * saber cual tipo de clase es la que va a ser usada en la conversión de JSON, ejemplo
     * Si utilizamos SerieOmdb (Record), el parametro generico seria SerieOmdb.class.
     * @return
     * @param <T>:  Esta parte indica que el metodo es genérico
     * @ T: es un parámetro de tipo que representa un tipo de dato que se especificará cuando se llame al metodo
     */
    <T> T otbenerDatos(String json, Class<T> clase);
}
