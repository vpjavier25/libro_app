package com.jvp.libros.repositorio;

import com.jvp.libros.modelo.Idioma;
import com.jvp.libros.modelo.IdiomaNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdiomaRepositorio extends JpaRepository<Idioma, Long> {
    Optional<Idioma> findByDescripcion(IdiomaNombre idioma);
}
