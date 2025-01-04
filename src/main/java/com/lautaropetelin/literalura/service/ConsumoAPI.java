package com.lautaropetelin.literalura.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumoAPI {

    public String obtenerDatos(String url){

        // Crear una instancia de HttpClient para realizar solicitudes HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construir una solicitud HTTP GET con la URL proporcionada
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviar la solicitud HTTP y obtener la respuesta
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Lanzar una RuntimeException si ocurre un error de E/S
            throw new RuntimeException("Error de E/S al obtener datos de la API", e);
        } catch (InterruptedException e) {
            // Lanzar una RuntimeException si la solicitud es interrumpida
            throw new RuntimeException("La solicitud fue interrumpida", e);
        }

        // Obtener el cuerpo de la respuesta en un String
        String json = response.body();
        return json;
    }
}