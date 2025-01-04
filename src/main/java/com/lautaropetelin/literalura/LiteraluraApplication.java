package com.lautaropetelin.literalura;

import com.lautaropetelin.literalura.principal.Menu;
import com.lautaropetelin.literalura.service.AutorService;
import com.lautaropetelin.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Menu menu = new Menu(libroService, autorService);

		System.out.println("\n-----------------------------------------------------------\n");

		System.out.println("¡Bienvenido a LiterAlura! | por Lautaro Petelin\n");
		menu.mostrarMenu();

		System.out.println("-----------------------------------------------------------");
	}
}