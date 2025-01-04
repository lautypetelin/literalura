package com.lautaropetelin.literalura.principal;

import com.lautaropetelin.literalura.dto.AutorDTO;
import com.lautaropetelin.literalura.dto.LibroDTO;
import com.lautaropetelin.literalura.dto.ResultadosDTO;
import com.lautaropetelin.literalura.model.Autor;
import com.lautaropetelin.literalura.model.Libro;
import com.lautaropetelin.literalura.service.AutorService;
import com.lautaropetelin.literalura.service.ConsumoAPI;
import com.lautaropetelin.literalura.service.ConversorDatos;
import com.lautaropetelin.literalura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {

    private Scanner entrada;
    private ConsumoAPI consumoAPI;
    private ConversorDatos conversorDatos;

    private String BASE_URL = "http://gutendex.com/books/";

    private LibroService libroService;
    private AutorService autorService;

    public Menu(LibroService libroService, AutorService autorService){
        entrada = new Scanner(System.in);
        consumoAPI = new ConsumoAPI();
        conversorDatos = new ConversorDatos();

        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu(){
        int opcion;
        boolean bandera = true;
        String menu = "-------------------------------------------------\n" +
                      "            *** Menú principal ***\t\t\t\t|\n" +
                      "\t\t\t\t\t\t\t\t\t\t\t\t|\n" +
                      "1. Buscar libro por título.\t\t\t            |\n" +
                      "2. Listar libros registrados.\t\t\t        |\n" +
                      "3. Listar autores registrados.\t\t\t        |\n" +
                      "4. Listar autores vivos en un determinado año.  |\n" +
                      "5. Listar libros por idioma.\t\t\t        |\n" +
                      "0. Salir.\t\t\t\t\t                    |\n" +
                      "-------------------------------------------------\n" +
                      "Seleccione una opción: ";
        while(bandera){
            try{
                System.out.print(menu);
                opcion = entrada.nextInt(); entrada.nextLine();

                System.out.println(); // Salto de línea

                switch(opcion){
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibros();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEnUnDeterminadoAnio();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("• ¡Hasta pronto!\n");
                        entrada.close();
                        bandera = false;
                        break;
                    default:
                        System.out.println("• La opción es inválida. Intente nuevamente.\n");
                }

            }catch (InputMismatchException e){
                System.out.println("\n• Debe ingresar un valor numérico.\n");
                entrada.nextLine();
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el titulo (o el nombre del autor) del libro que desea buscar: ");
        String tituloLibro = entrada.nextLine();

        String json = consumoAPI.obtenerDatos(BASE_URL + "?search=" + tituloLibro.replace(" ", "%20"));
        ResultadosDTO resultados = conversorDatos.convertirDatos(json, ResultadosDTO.class);
        List<LibroDTO> librosDTO = resultados.libros();

        System.out.println(); // Salto de línea

        if(librosDTO.isEmpty()){
            System.out.println("• Libro no encontrado en la API.\n");
            return;
        }

        Optional<Libro> libroExistenteOpt = libroService.obtenerLibroPorTitulo(resultados.libros().stream().findFirst().get().titulo()); // resultados.libros().stream().findFirst().get().titulo()
        // Informar al usuario de la existencia del libro
        if(libroExistenteOpt.isPresent()){
            System.out.println("• El libro \'" + libroExistenteOpt.get().getTitulo() + "\' ya se encuentra en la base de datos.");
        }else{
            // Crear un nuevo libro
            Libro libroNuevo = new Libro(resultados);

            // Buscar o crear autor para el nuevo libro
            AutorDTO primerAutorDTO = librosDTO.stream().findFirst().get().autores().get(0);
            Optional<Autor> autor = autorService.obtenerAutorPorNombre(primerAutorDTO.nombre());
            if(!autor.isPresent()){
                Autor autorNuevo = new Autor(resultados);
                autorService.crearAutor(autorNuevo);

                // Asignar autor al libro nuevo
                libroNuevo.setAutor(autorNuevo);

                // Guardar libro en la base de datos
                libroService.crearLibro(libroNuevo);
                System.out.println(libroNuevo);
                System.out.println("• Libro guardado correctamente.");
            }
        }

        System.out.println(); // Salto de línea
    }

    private void listarLibros() {
        List<Libro> libros = libroService.listarLibros();

        if(libros.isEmpty()){
            System.out.println("• No hay libros cargados en la base de datos.");
            return;
        }

        System.out.println("• Libros registrados:\n");
        libros.forEach(l -> System.out.println(l));
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.listarAutores();

        if(autores.isEmpty()){
            System.out.println("• No hay autores cargados en la base de datos.");
            return;
        }

        System.out.println("• Autores registrados:\n");
        autores.forEach(a -> System.out.println("Autor: " + a));
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.print("Digíte el año que desea averiguar autores vivos: ");
        int anio = entrada.nextInt();

        List<Autor> autoresVivos = autorService.listarAutoresVivosEnAnio(anio);

        System.out.println(); // Salto de línea

        if(autoresVivos.isEmpty()){
            System.out.println("• No hay autores vivos en el año " + anio + ".\n");
            return;
        }

        System.out.println("• Autores vivos en el año " + anio + ":\n");
        autoresVivos.forEach(a -> System.out.println(a.getNombre() + " (" + a.getAnioNacimiento() + "/" + a.getAnioFallecimiento() + ")"));

        System.out.println();  // Salto de línea
    }

    private void listarLibrosPorIdioma() {
        System.out.print("*** Idiomas ***\n\n" +
                "1. Español.\n" +
                "2. Inglés.\n" +
                "3. Francés.\n" +
                "4. Portugués.\n" +
                "5. Chino.\n" +
                "\nSeleccione una opción: ");

        int opcion = entrada.nextInt();
        entrada.nextLine();

        System.out.println(); // Salto de línea

        String idioma;
        switch (opcion) {
            case 1:
                idioma = "es";
                break;
            case 2:
                idioma = "en";
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "pt";
                break;
            case 5:
                idioma = "zh";
                break;
            default:
                System.out.println("• La opcion elegida no es válida.\n");
                return;
        }

        List<Libro> libros = libroService.ObtenerLibroPorIdioma(idioma);

        if(libros.isEmpty()){
            System.out.println("• No se encontraron libros en el idiomas solicitado.\n");
            return;
        }

        System.out.println("• Libros en el idiomas \'" + idioma + "\':\n");
        libros.forEach(a -> System.out.println(a));
    }
}