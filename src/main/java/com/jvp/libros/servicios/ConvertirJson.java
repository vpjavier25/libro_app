package com.jvp.libros.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirJson {
    private final ObjectMapper mapper = new ObjectMapper();
    public <T> T aObjeto(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }catch (JsonProcessingException e){
            System.out.println("Error en este");
            throw new RuntimeException(e);
        }
    }
}
