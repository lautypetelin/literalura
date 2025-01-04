package com.lautaropetelin.literalura.repository;

import com.lautaropetelin.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Optional<Libro> findByTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idiomas")
    List<Libro> findByIdioma(@Param("idiomas") String idioma);
}