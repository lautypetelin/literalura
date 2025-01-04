package com.lautaropetelin.literalura.service;

public interface IConversorDatos {

    <T> T convertirDatos(String json, Class<T> clase);
}