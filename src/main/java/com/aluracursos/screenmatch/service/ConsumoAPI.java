package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.IO.OmdbException;
import com.aluracursos.screenmatch.IO.OmdbNotFoundException;
import com.aluracursos.screenmatch.IO.OmdbUnauthorizedException;
import com.aluracursos.screenmatch.models.OmdbErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumoAPI {

    private static final String URL_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=";
    private static final String API_KEY_ID = "cd4495e5";
    private final HttpClient httpClient;

    public ConsumoAPI(){
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * @param nombreSerie
     * @param numeroTemporada
     * @return
     * @throws IOException
     * @throws InterruptedException
     * Metodo que recive por parametro un nombre de serie y una temporada especifica y retorna la información
     * que proviene desde la API
     */
    public String buscadorSeriePorTemporada(String nombreSerie, String numeroTemporada) throws IOException, InterruptedException {
        URI uri = uriBusquedaSeriePorTemporada(nombreSerie, numeroTemporada);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new RuntimeException("Error HTTP: " + response.statusCode());
        }


        return response.body();
    }
    /**
     *
     * @param nombreSerie
     * @param numeroTemporada
     * @return
     */

    private URI uriBusquedaSeriePorTemporada(String nombreSerie, String numeroTemporada){
        String encoderUrl = URLEncoder
                .encode(String.valueOf(nombreSerie), StandardCharsets.UTF_8);
        String url = URL_BASE + encoderUrl +"&Season="+ numeroTemporada+ API_KEY + API_KEY_ID;
        return URI.create(url);
    }

    /**
     * @param nombreSerie
     * @return
     */

    public String buscadorSerie(String nombreSerie) throws IOException, InterruptedException {
        URI uri = uriBuscadorSerie(nombreSerie);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new RuntimeException("Error HTTP: " + response.statusCode());
        }

        ObjectMapper mapper = new ObjectMapper();
        OmdbErrorResponse errorResponse = mapper.readValue(response.body(), OmdbErrorResponse.class);

        if ("False".equalsIgnoreCase(errorResponse.getResponse())) {
            String msgError = errorResponse.getError();

            if (msgError.contains("Movie not found!")) {
                throw new OmdbNotFoundException("Película/Serie no encontrada: " + nombreSerie);
            }

            if (msgError.contains("API key") || msgError.contains("Unauthorized")) {
                throw new OmdbUnauthorizedException("Sin acceso al sistema: API Key inválida.");
            }

            throw new OmdbException("Error OMDb: " + msgError);
        }


        return response.body();
    }
    // buscarSerieTemporadaEpisodio

    public String buscadorSerieTemporadaEpisodio(String nombreSerie, String temporada, String episode) throws IOException, InterruptedException {
        URI uri = uriBuscadorSerieTemporadaEpisodio(nombreSerie, temporada, episode);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200){
            throw new RuntimeException("Error HTPP: " + response.statusCode());
        }

        return response.body();
    }

    /**
     *
     * @param nombreSerie
     * @return
     */

    private URI uriBuscadorSerie(String nombreSerie){
        String  encoderUrl = URLEncoder
                .encode(String.valueOf(nombreSerie), StandardCharsets.UTF_8);
        String url = URL_BASE + encoderUrl + API_KEY + API_KEY_ID;
        return URI.create(url);
    }

    private URI uriBuscadorSerieTemporadaEpisodio(String nombreSerie, String temporada, String episode){
        String encodeUrl = URLEncoder
                .encode(String.valueOf(nombreSerie) ,StandardCharsets.UTF_8);
        String url = URL_BASE + encodeUrl +"&Season="+temporada+"&Episode="+episode+"&"+API_KEY+API_KEY_ID;
        return URI.create(url);
    }

    private String encodeURL(String data){
        return URLEncoder.encode(String.valueOf(data), StandardCharsets.UTF_8);
    }
}
