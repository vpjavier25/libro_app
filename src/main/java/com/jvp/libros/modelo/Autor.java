package com.jvp.libros.modelo;

import com.jvp.libros.dto.DatosAutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();


    public Autor(DatosAutor da){
        this.nombre = da.nombre();
        this.fechaNacimiento = da.fechaDeNacimiento();
        this.fechaMuerte = da.fechaDeMuerte();
    }

    public Autor() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void addLibro(Libro libro){
        this.libros.add(libro);
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.addAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Titulo: " + nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) && Objects.equals(fechaNacimiento, autor.fechaNacimiento) && Objects.equals(fechaMuerte, autor.fechaMuerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaNacimiento, fechaMuerte);
    }
}
