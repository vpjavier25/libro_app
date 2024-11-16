package com.jvp.libros;

import com.jvp.libros.vista.MenuPrincipalVista;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class LibrosApplication implements CommandLineRunner{

	private MenuPrincipalVista vista;

	public LibrosApplication(MenuPrincipalVista vista){
		this.vista = vista;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		vista.iniciar();
	}
}
