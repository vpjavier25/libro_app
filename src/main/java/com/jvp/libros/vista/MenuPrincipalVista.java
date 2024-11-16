package com.jvp.libros.vista;

import com.jvp.libros.controlador.AutorControlador;
import com.jvp.libros.controlador.IdiomaControlador;
import com.jvp.libros.controlador.LibroControlador;
import com.jvp.libros.modelo.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuPrincipalVista {

    private LibroControlador libroControlador;
    private AutorControlador autorControlador;
    private IdiomaControlador idiomaControlador;

    private final Scanner entrada = new Scanner(System.in);

    @Autowired
    public void setLibroControlador(LibroControlador libroControlador){
        this.libroControlador = libroControlador;
    }

    @Autowired
    public void setAutorControlador(AutorControlador autorControlador){
        this.autorControlador = autorControlador;
    }

    @Autowired
    public void setIdiomaControlador(IdiomaControlador idiomaControlador){
        this.idiomaControlador = idiomaControlador;
    }

    private void mostrarOpciones() {
        System.out.println("0. Salir");
        System.out.println("1. Registrar libro por titulo");
        System.out.println("2. Listar libros registrados");
        System.out.println("3. Listar autores registrados");
        System.out.println("4. Listar autores vivos en un año determinado");
        System.out.println("5. Listar libros por idioma");

    }

    private int leerEntero(){
        int o;
        try{
            o = entrada.nextInt();
            entrada.nextLine();
            return o;
        } catch (InputMismatchException e){
            throw new RuntimeException();
        }
    }

    public String leerCadenaTexto(){
        String s;
        try{
            s = entrada.nextLine();
            return s;
        } catch (InputMismatchException e){
            throw new RuntimeException();
        }
    }

    private void seleccionarOpciones(){
        int opcion;
        mostrarOpciones();
        opcion = leerEntero();
        if(opcion <= 5 && opcion >= 0 ){
            switch(opcion){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Indique el titulo del libro");
                    String titulo = leerCadenaTexto();
                    libroControlador.obtenerLibroPorTitulo(titulo);
                    seleccionarOpciones();
                   break;
                case 2:
                    System.out.println("Los libros registrados son: ");
                    libroControlador.obtenerTodosLoslibros();
                    seleccionarOpciones();
                    break;
                case 3:
                    System.out.println("Los autores registrados son: ");
                    autorControlador.obtenerTodosLosAutores();
                    seleccionarOpciones();
                    break;
                case 4:
                    System.out.println("Ingrese el año: ");
                    Integer anio = leerEntero();
                    System.out.println("Los autores vivos el año especificado son: ");
                    autorControlador.obtenerAutoresVivos(anio);
                    seleccionarOpciones();
                    break;
                case 5:
                    System.out.println("Seleccione el idioma:");
                    List<Idioma> idiomas = idiomaControlador.obtenerTodosLosIdiomas();
                    for(int i=0; i<idiomas.size(); i++){
                        System.out.println(i + 1 + ". " + idiomas.get(i).getDescripcion().getNombre() + " - " + idiomas.get(i).getDescripcion().getCodigo());
                    }
                    Integer idiomaPos = leerEntero();
                    if (idiomaPos > idiomas.size()){
                        System.out.println("Opcion invalida");
                        seleccionarOpciones();
                    }
                    libroControlador.obtenerLibrosPorIdioma(idiomas.get(idiomaPos - 1));
            }
        } else {
            System.out.println("Error,la operacion no existe");
        }
    }

    public void iniciar(){
        seleccionarOpciones();
    }
}

