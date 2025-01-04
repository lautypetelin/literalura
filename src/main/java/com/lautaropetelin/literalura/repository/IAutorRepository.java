package com.lautaropetelin.literalura.repository;

import com.lautaropetelin.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Optional<Autor> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE (a.anioFallecimiento IS NULL OR a.anioFallecimiento > :anio) AND a.anioNacimiento <= :anio")
    List<Autor> findAutoresVivosEnAnioConLibros(@Param("anio") int anio);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllConLibros();
}