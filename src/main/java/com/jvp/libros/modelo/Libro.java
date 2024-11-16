package com.jvp.libros.modelo;

import com.jvp.libros.dto.DatosLibro;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private int numeroDeDescargas;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List<Idioma> idiomas = new ArrayList<>();

    public Libro(DatosLibro dl){
        this.titulo = dl.titulo();
        this.numeroDeDescargas = dl.numeroDeDescargas();
    }

   public Libro() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void addAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.addLibro(this));
        this.autores = autores;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
    }

    public void setIdiomas(List<Idioma> idiomas) {
        idiomas.forEach(a -> a.addLibro(this));
        this.idiomas = idiomas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return numeroDeDescargas == libro.numeroDeDescargas && Objects.equals(titulo, libro.titulo) && Objects.equals(autores, libro.autores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autores, numeroDeDescargas);
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo
                + ", Autores: " + autores.stream().map(Autor::getNombre).collect(Collectors.joining("||"))
                + ", Numero de descargas: " + numeroDeDescargas;
    }
}
