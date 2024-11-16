package com.jvp.libros.servicios;

import com.jvp.libros.modelo.Autor;
import com.jvp.libros.repositorio.AutorRepositorio;
import com.jvp.libros.util.FormatearNombreAutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServicio {
    private AutorRepositorio autorRepositorio;

    public AutorServicio(AutorRepositorio autorRepositorio){
        this.autorRepositorio = autorRepositorio;
    }

    public void obtenerTodosLosAutores(){
        List<Autor> autores = autorRepositorio.findAll();
        if(autores.isEmpty()){
            System.out.println("No ahi autores registrados");
            return;
        }
        autores.forEach(this::imprimirAutor);
    }

    public void obtenerAutoresVivos(Integer anio) {
        List<Autor> autores = autorRepositorio.findAutoresVivosByAnio(anio);
        if(autores.isEmpty()){
            System.out.println("No hay actores vivos en el año especificado");
            return;
        }
        autores.forEach(this::imprimirAutor);
    }

    public void imprimirAutor(Autor autor){
        System.out.println("Nombre: " + FormatearNombreAutor.formatearNombre(autor.getNombre())
                + " Fecha de nacimiento: " + autor.getFechaNacimiento()
                + " Fecha de fallecimiento: " + autor.getFechaMuerte());
    }
}
