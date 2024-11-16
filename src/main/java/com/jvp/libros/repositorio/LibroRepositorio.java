package com.jvp.libros.repositorio;

import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE :idioma = i")
    List<Libro> findByIdioma(Idioma idioma);
}
