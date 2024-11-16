package com.jvp.libros.controlador;

import com.jvp.libros.servicios.AutorServicio;
import org.springframework.stereotype.Controller;

@Controller
public class AutorControlador {
    private AutorServicio servicio;

    public AutorControlador(AutorServicio autorServicio){
        this.servicio = autorServicio;
    }

    public void obtenerTodosLosAutores(){
        servicio.obtenerTodosLosAutores();
    }

    public void obtenerAutoresVivos(Integer anio){
        servicio.obtenerAutoresVivos(anio);
    }
}
