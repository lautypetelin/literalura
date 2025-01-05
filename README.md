# LiterAlura

Este es un proyecto desarrollado en Java con Spring Boot que permite explorar y buscar información sobre libros mediante la API de Gutendex. Este proyecto obtiene datos detallados de libros y sus autores.

![api](https://github.com/user-attachments/assets/a240fd2b-850d-49d1-9b3f-1f8dc7dac993)

## Funcionalidades

1. **Buscar libro por título:** Realiza búsquedas de libros específicos por título (o por autor del mismo), obteniendo información detallada sobre ellos.
2. **Listar libros registrados:** Consulta una lista de los libros almacenados en la base de datos.
3. **Listar autores registrados:** Obtén una lista de los autores cuyos libros están registrados en el sistema.
4. **Listar autores vivos en un determinado año:** Encuentra autores que estaban vivos en un año específico, ideal para investigaciones históricas.
5. **Listar libros por idioma:** Filtra y muestra libros según su idioma, permitiendo una exploración más focalizada.

![menu](https://github.com/user-attachments/assets/1b748fa4-71bd-423a-86e0-cee3df4b14af)

## Tecnologías utilizadas

- **Lenguaje de programación:** Java
- **Framework:** Spring (con las especificaciones Spring Boot y Spring Data)
- **Dependencias:** Jackson Databind y relacionadas a Spring Boot y Spring Data, mediante el gestor Maven
- **API:** Gutendex (Proveedor DE información sobre libros y autores)
- **JPA (Java Persistence API):** Implementación de Hibernate.
- **Base de Datos:** PostgreSQL

## Uso

1. Clonar el repositorio.
2. Configurar las variables de entorno ${DB_HOST}, ${DB_NAME}, ${DB_USER} y ${DB_PASSWORD}.
3. Ejecutar el programa.
4. Navegar por las opciones.
7. Repetir el proceso o salir del programa.

## Créditos

- **Desarrollado por**: Lautaro Tomás Israel Petelin
- **Tecnologías utilizadas**: Java 17.0.6
- **IDE**: IntelliJ IDEA 17
- **Autor de la consigna**: Alura Latam (ONE)

## Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes sugerencias de mejora, no dudes en abrir un problema o enviar una pull request.
