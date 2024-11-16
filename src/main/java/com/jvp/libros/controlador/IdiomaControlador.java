package com.jvp.libros.controlador;

import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.servicios.IdiomaServicio;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IdiomaControlador {
    private IdiomaServicio idiomaServicio;

    public IdiomaControlador(IdiomaServicio idiomaServicio) {
        this.idiomaServicio = idiomaServicio;
    }

    public List<Idioma> obtenerTodosLosIdiomas(){
        return idiomaServicio.obtenerIdiomas();
    }
}
