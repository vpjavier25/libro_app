package com.jvp.libros.servicios;

import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.repositorio.IdiomaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaServicio {
    private IdiomaRepositorio idiomaRepositorio;

    public IdiomaServicio(IdiomaRepositorio idiomaRepositorio) {
        this.idiomaRepositorio = idiomaRepositorio;
    }

    public List<Idioma> obtenerIdiomas() {
        List<Idioma> idiomas = idiomaRepositorio.findAll();
        if(idiomas.isEmpty()) {
            System.out.println("No hay idiomas registrados en la base de datos");
            return null;
        }
        return idiomas;
    }
}
