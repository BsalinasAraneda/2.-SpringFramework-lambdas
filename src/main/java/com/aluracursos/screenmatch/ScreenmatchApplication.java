package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.DTOs.EpisodioOmdb;
import com.aluracursos.screenmatch.DTOs.SerieOmdb;
import com.aluracursos.screenmatch.DTOs.TemporadasOmdb;
import com.aluracursos.screenmatch.principal.LanzarAplicacion;
import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		/*var consumoApi = new ConsumoAPI();
		String nombreSerie = "game of thrones";
		//var jsonSerieTemporada = consumoApi.buscarSerieTemporada("game of thrones","2");

		var jsonGetDatosSerie = consumoApi.buscadorSerie(nombreSerie);
		var jsonGetEpisodioTemporada = consumoApi.buscadorSerieTemporadaEpisodio(nombreSerie,"1","1");

		// Metodo que retorna el ObjectMapper con los datos obtenidos de la respuesta Json correspondiente
		// Al objeto generico que recibe el metodo
		// @conversor ==> Corresponde a la instancia de la clase, para acceder al metodo ObjectMapper()
		ConvierteDatos conversor = new ConvierteDatos();
		var datosSerie = conversor.otbenerDatos(jsonGetDatosSerie, SerieOmdb.class);
		var datosTemporadaEpisodio = conversor.otbenerDatos(jsonGetEpisodioTemporada, EpisodioOmdb.class);

		//System.out.println(datosSerie);
		//System.out.println(datosTemporadaEpisodio);

		*//**
		 * Pasos para enteder la lofica del for()
		 * 1.- Para recorrer el total de temporadas debemos saber exactamente cuantas temporadas tiene, por lo que mediante
		 * la variable datosSerie que almacena el Record de SerieOmdb.class(), accedemos a la propiedad de totalTemporadas.
		 * Esto sabiendo que internamente esta siendo trabajado por el ObjectMapper()
		 *
		 * 2.- jsonGetAllDatosSerie, almacenamos la respuesta que retorna el metodo buscadorSeriePorTemporada(), pero en este
		 * caso le pasamos por parametro el numero de la temporada como un iterador el cual de Integer es convertido a String
		 * de tal forma que la URL lo pueda interpretar como tal.
		 *
		 * 3.- En la variable datosTemporada, llamamos al ObjectMapper() y le pasamos por parametro el Json de la respuesta
		 * de la API y convertimos el Mapper() a un objeto del tipo Record TemporadaOmdb, lo que permite obtener los datos.
		 *
		 * 4.- Para visualizar la información
		 **//*
		List<TemporadasOmdb> listaTemporadas = new ArrayList<>();
		for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
			var jsonGetAllDatosSerie = consumoApi.buscadorSeriePorTemporada(nombreSerie, String.valueOf(i));
			var datosTemporada = conversor.otbenerDatos(jsonGetAllDatosSerie, TemporadasOmdb.class);
			listaTemporadas.add(datosTemporada);
		}

		listaTemporadas.forEach(System.out::println);
		*/

		// Principal lanzador = new Principal();
		// lanzador.lanzarMenu();

        LanzarAplicacion lanzarAPlicacion = new LanzarAplicacion();
        lanzarAPlicacion.iniciarMenu();




	}
}
