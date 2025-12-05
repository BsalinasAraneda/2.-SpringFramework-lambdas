package com.aluracursos.screenmatch.principal;

import ch.qos.logback.core.joran.spi.NoAutoStartUtil;
import com.aluracursos.screenmatch.DTOs.EpisodioOmdb;
import com.aluracursos.screenmatch.DTOs.PeliculaOmdb;
import com.aluracursos.screenmatch.DTOs.SerieOmdb;
import com.aluracursos.screenmatch.DTOs.TemporadasOmdb;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzarAplicacion {
    private Scanner sc = new Scanner(System.in);
    private Thread thread = new Thread();

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<TemporadasOmdb> temporadasOmdbList = new ArrayList<>();

    private String contenidoBuscado;

    public void iniciarMenu() throws InterruptedException, IOException {

        while (true) {

            System.out.println("***** API OMDB *****");
            System.out.println("[1] Obtener información de peliculas");
            System.out.println("[2] Obtener series y sus temporadas");
            System.out.println("[3] Para salir...");
            int opcMenu = sc.nextInt();
            sc.nextLine();

            if (opcMenu == 4) {
                break;
            } else {
                System.out.println("Ingrese el nombre del contenido a buscar");
                contenidoBuscado = sc.nextLine();
                var jsonGetContenido = consumoAPI.buscadorSerie(contenidoBuscado); // Filtro de contenido
                var jsonContendioMovie = convierteDatos.otbenerDatos(jsonGetContenido, PeliculaOmdb.class); // Filtro peliculas
                var jsonGetDatosSerie = convierteDatos.otbenerDatos(jsonGetContenido, SerieOmdb.class); // Filtro de la serie

                switch (opcMenu) {
                    case 1:
                        System.out.println("*** Opcion de Peliculas ***");
                        if (jsonContendioMovie.tituloPelicula() == null) {
                            System.out.println("El contenido [" + contenidoBuscado + "] no es valido");
                        } else {
                            System.out.println(jsonContendioMovie);
                            thread.sleep(2000);
                        }

                        break;
                    case 2:
                        if (jsonGetDatosSerie.totalTemporadas() == null) {
                            System.out.println("El contenido [" + contenidoBuscado + "] no es valido");
                        }

                        System.out.println("*** Datos de la Serie buscada***");
                        System.out.println(jsonGetDatosSerie);

                        for (int i = 1; i <= jsonGetDatosSerie.totalTemporadas(); i++) {
                            // 5.- Llamamos al metodo buscadorSeriePorTemporada() pasamos el nombre del contenido
                            // y pasamos el iterador como argumento en la URL para que consulte en la API, con la consideración
                            // de convertir el Integer a un String.valueof()
                            var jsonGetAllData = consumoAPI.buscadorSeriePorTemporada(contenidoBuscado, String.valueOf(i));
                            // 6.- Obtenidos los datos del buscador de temporadas, pasamos la respuesta del Json a nuestro
                            // ObjectMapper() y lo convertimos a nuestro Record() TemporadasOmdb()
                            var datosTemporada = convierteDatos.otbenerDatos(jsonGetAllData, TemporadasOmdb.class);
                            temporadasOmdbList.add(datosTemporada);
                            temporadasOmdbList.forEach(System.out::println);
                        }

                        thread.sleep(2000);
                        System.out.println("Contenido recuperado con exito!!!");

                        System.out.println("Desees visaluzar los episodios de forma mas compacta?");
                        System.out.println("S - Para ordenar \nN- para abandonar");
                        var respuesta = sc.nextLine();

                        switch (respuesta.toLowerCase()) {
                            case "s":
                                System.out.println("Generando resumen.... " + contenidoBuscado);
                                thread.sleep(2000);

                                //Mostrar información resumida utilizando bucles anidados
                                //Se generan dos blucles
                                // Bucle i para recuperrar la información desde la List<temporadasOmdbList> y obtener los episodios
                                for (int i = 0; i < jsonGetDatosSerie.totalTemporadas(); i++) {

                                    //Creacion de nuevo List<> para almacenar los datos
                                    List<EpisodioOmdb> episodioOmdbList = temporadasOmdbList.get(i).episodios();
                                    // For J para iterar sobre toda la lista de episodios que generamos en el List<>
                                    for (int j = 0; j < episodioOmdbList.size(); j++) {
                                        System.out.println("Temporda:" + temporadasOmdbList.get(i).numeroTemporada() +
                                                "-->Episodio:" + episodioOmdbList.get(j).numeroEpisodio() +
                                                "-->Titulo:" + episodioOmdbList.get(j).titulo());
                                    }
                                }
                                System.out.println("Utilizando Lambdas");
                                temporadasOmdbList.forEach(temporadas -> temporadas.episodios()
                                        .forEach(episodios -> System.out.println("Temporada:" +
                                                temporadas.numeroTemporada() + " --> Episodio:" +
                                                episodios.numeroEpisodio() + " --> Titulo: " +
                                                episodios.titulo())));

                                thread.sleep(2000);
                                break;
                            case "n":
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Gracias por usar nuestro servicio");
                        thread.sleep(2000);
                        break;
                    default:
                        System.out.println("La opción ingresada no es valida...");
                        thread.sleep(2000);
                        break;

                }
            }
        }
    }

}
