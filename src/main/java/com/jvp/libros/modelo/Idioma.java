package com.jvp.libros.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(value = EnumType.STRING)
    private IdiomaNombre descripcion;

    @ManyToMany(mappedBy = "idiomas", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Idioma (String codigo){
        this.descripcion = IdiomaNombre.fromCodigo(codigo);
    }

    public Idioma(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IdiomaNombre getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(IdiomaNombre descripcion) {
        this.descripcion = descripcion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void addLibro(Libro libro){
        this.libros.add(libro);
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.addIdioma(this));
        this.libros = libros;
    }

    @Override
    public String toString(){
        return "idioma: "+descripcion;
    }

}
