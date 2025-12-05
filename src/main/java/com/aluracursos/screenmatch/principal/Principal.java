package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.DTOs.SerieOmdb;
import com.aluracursos.screenmatch.DTOs.TemporadasOmdb;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void lanzarMenu() throws IOException, InterruptedException {
        System.out.println("****** API OMDB ****** \n");
        System.out.println("Ingrese el nombre de la pelicula");
        String nombrePelicula = sc.nextLine();

        // 1.- Filtramos por el nombre del contenido, en este caso seria una serie y sus temporadas
        var jsonGetDatosSerie = consumoAPI.buscadorSerie(nombrePelicula);
        // 2.- Obtenemos los datos de la serie y convertimos ese JSON en el contenido del Record() SerieOmdb
        var getDatosSerie = convierteDatos.otbenerDatos(jsonGetDatosSerie, SerieOmdb.class);


        // 3.- Creamos una lista para almacenar las temporadas asociadas a la serie
        List<TemporadasOmdb> listaTemporadas = new ArrayList<>();
        // 4.- Generamos un blucle ford, dentro del cual dentro de datosSerie() obtenemos el total de tempordas
        // Y llamamos a la API por cada numero episodio encontrado ahi

        System.out.println(getDatosSerie);
        for (int i = 1; i <= getDatosSerie.totalTemporadas(); i++) {
            // 5.- Llamamos al metodo buscadorSeriePorTemporada() pasamos el nombre del contenido
            // y pasamos el iterador como argumento en la URL para que consulte en la API, con la consideración
            // de convertir el Integer a un String.valueof()
            var jsonGetAllData = consumoAPI.buscadorSeriePorTemporada(nombrePelicula, String.valueOf(i));
            // 6.- Obtenidos los datos del buscador de temporadas, pasamos la respuesta del Json a nuestro
            // ObjectMapper() y lo convertimos a nuestro Record() TemporadasOmdb()
            var datosTemporada = convierteDatos.otbenerDatos(jsonGetAllData, TemporadasOmdb.class);
            listaTemporadas.add(datosTemporada);

            listaTemporadas.forEach(System.out::println);

        }

    }
}