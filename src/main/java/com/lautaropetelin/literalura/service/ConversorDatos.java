package com.lautaropetelin.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConversorDatos implements IConversorDatos{

    private ObjectMapper objectMapper;

    public ConversorDatos(){
        objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserealizar JSON", e);
        }
    }
}