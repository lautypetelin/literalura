package com.lautaropetelin.literalura.model;

import com.lautaropetelin.literalura.dto.ResultadosDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long idAutor;

    @Column(name = "nombre", unique = true)
    private String nombre;
    private int anioNacimiento;
    private int anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    // Constructor
    public Autor(){}

    public Autor(ResultadosDTO resultados){
        this.nombre = resultados.libros().stream().findFirst().get().autores().stream().findFirst().get().nombre();
        this.anioNacimiento = resultados.libros().stream().findFirst().get().autores().stream().findFirst().get().anioNacimiento();
        this.anioFallecimiento = resultados.libros().stream().findFirst().get().autores().stream().findFirst().get().anioFallecimiento();
    }

    // Getters y Setters
    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(int anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString(){
        return nombre + " (" + anioNacimiento + "-" + anioFallecimiento + ")\n" +
               "Libros: " + libros.stream().map(l -> l.getTitulo()).collect(Collectors.toList()) + "\n";
    }
}