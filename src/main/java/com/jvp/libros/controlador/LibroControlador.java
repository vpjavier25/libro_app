package com.jvp.libros.controlador;

import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.modelo.IdiomaNombre;
import com.jvp.libros.servicios.LibroServicio;
import org.springframework.stereotype.Component;

@Component
public class LibroControlador {


    private LibroServicio servicio;

    public LibroControlador(LibroServicio servicio){
        this.servicio = servicio;
    }

    public void obtenerLibroPorTitulo(String titulo) {
        servicio.obtenerLibroPorNombre(titulo);
    }

    public void obtenerTodosLoslibros() {
        servicio.obtenerTodosLosLibros();
    }

    public void obtenerLibrosPorIdioma(Idioma idioma){
        servicio.obtenerLibrosPorIdioma(idioma);
    }
}
