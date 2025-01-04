package com.lautaropetelin.literalura.model;

import com.lautaropetelin.literalura.dto.ResultadosDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private int numDescargas;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    // Constructor
    public Libro(){}

    public Libro(ResultadosDTO resultados){
        this.titulo = resultados.libros().stream().findFirst().get().titulo();
        this.idioma = resultados.libros().stream().findFirst().get().idiomas().stream().findFirst().get();
        this.numDescargas = resultados.libros().stream().findFirst().get().numDescargas();
    }

    // Getters y Setters
    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // toString
    @Override
    public String toString(){
        return "Titulo: " + titulo +
               "\nAutor: " + (autor != null ? autor.getNombre() : "Desconocido") +
               "\nIdioma: " + idioma +
               "\nNúmero de descargas: " + numDescargas + "\n";
    }
}