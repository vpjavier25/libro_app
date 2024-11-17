package com.jvp.libros.servicios;

import com.jvp.libros.dto.DatosAutor;
import com.jvp.libros.dto.DatosLibro;
import com.jvp.libros.dto.DatosLibros;
import com.jvp.libros.modelo.Autor;
import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.modelo.IdiomaNombre;
import com.jvp.libros.modelo.Libro;
import com.jvp.libros.repositorio.AutorRepositorio;
import com.jvp.libros.repositorio.IdiomaRepositorio;
import com.jvp.libros.repositorio.LibroRepositorio;
import com.jvp.libros.util.FormatearNombreAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepositorio libroRepositorio;
    private IdiomaRepositorio idiomaRepositorio;
    private AutorRepositorio autorRepositorio;
    private ConsumirApi api = new ConsumirApi();
    private ConvertirJson c = new ConvertirJson();

    public LibroServicio(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    @Autowired
    public void setIdiomaRepositorio(IdiomaRepositorio idiomaRepositorio) {
        this.idiomaRepositorio = idiomaRepositorio;
    }

    @Autowired
    public void setAutorRepositorio(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    public void obtenerLibroPorNombre(String titulo) {
        String librosJson = api.obtenerDatos(URL_BASE+titulo.replace(" ", "+"));
        DatosLibros resultado = c.aObjeto(librosJson, DatosLibros.class);

        if(resultado.libros().isEmpty()){
            System.out.println("No se encontro el libro buscado");
            return;
        }

        DatosLibro datosLibro = resultado.libros().getFirst();

        Optional<Libro> existLibro = libroRepositorio.findByTitulo(datosLibro.titulo());

        if (existLibro.isPresent()) {
            System.out.println("El libro existe en la base de datos");
            return;
        }


        Libro libro = new Libro(datosLibro);
        libroRepositorio.save(libro);
        List<Idioma> idiomas = new ArrayList<>();
        for (String idioma : datosLibro.idiomas()) {
            Optional<Idioma> existeIdioma = idiomaRepositorio.findByDescripcion(IdiomaNombre.fromCodigo(idioma));
            if (existeIdioma.isEmpty()) {
                Idioma idiomaGuardar = idiomaRepositorio.save(new Idioma(idioma));
                idiomas.add(idiomaGuardar);
            } else {
                idiomas.add(existeIdioma.get());
            }
        }

        List<Autor> autores = new ArrayList<>();
        for (DatosAutor autor : datosLibro.autores()) {
            Optional<Autor> existeAutor = autorRepositorio.findByNombre(autor.nombre());
            if (existeAutor.isEmpty()) {
                Autor autorGuardar = autorRepositorio.save(new Autor(autor));
                autores.add(autorGuardar);
            } else {
                autores.add(existeAutor.get());
            }
        }


        libro.setAutores(autores);
        libro.setIdiomas(idiomas);

        libroRepositorio.save(libro);

        imprimirLibro(libro);
    }

    public void obtenerTodosLosLibros() {
        List<Libro> libros = libroRepositorio.findAll();

        if(libros.isEmpty()){
            System.out.println("Aúun no hay libros registrados en la base de datos");
            return;
        }

        libros.forEach(this::imprimirLibro);
    }

    public void obtenerLibrosPorIdioma(Idioma idioma) {
        List<Libro> libros = libroRepositorio.findByIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("No se encontraron libros para el idioma especificado");
        }
        libros.forEach(this::imprimirLibro);
    }

    private void imprimirLibro(Libro libro) {
        StringBuilder idiomasString = new StringBuilder();
        StringBuilder autoresString = new StringBuilder();
        System.out.println("-----------LIBRO-----------");
        System.out.println("Titulo: " + libro.getTitulo());
        libro.getAutores().forEach(a -> autoresString.append(FormatearNombreAutor.formatearNombre(a.getNombre())));
        System.out.println("Autor/es: " + autoresString);
        libro.getIdiomas().forEach(i -> idiomasString.append(i.getDescripcion()).append(" "));
        System.out.println("Idioma/s: " + idiomasString);
        System.out.println("Numero de descargas: " + libro.getNumeroDeDescargas());
        System.out.println("---------------------------");
    }


}
