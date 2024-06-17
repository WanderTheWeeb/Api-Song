# Proyecto Spring Boot con MongoDB en Docker
>Este proyecto es una aplicación API REST desarrollada con Spring Boot que gestiona una colección de canciones. La aplicación utiliza MongoDB como base de datos y se ejecuta en un contenedor Docker.


## Tabla de contenidos
* [Instalación](#instalación)
* [Información general](#información-general)
* [Tecnologías](#tecnologías)
* [Contribuciones](#contribuciones)

---

## Instalación

## Con GitHub 

#### Clonar el repositorio

```bash
 git clone https://github.com/WanderTheWeeb/Api-Song 
```

#### Compilar el proyecto

```bash
 mvn clean install
```

#### Ejecutar el proyecto

```bash
 mvn spring-boot:run
```

## Con Docker

#### Obtener la imagen de Docker

```bash
 docker pull wandertheweeb/api-song
```

#### Ejecutar el contenedor de Docker

```bash
 docker run -p 8080:8080 api-song
```
---

## Información general
La aplicación API REST permite realizar operaciones CRUD sobre una colección de canciones. Las operaciones disponibles son las siguientes:

## Endpoints de la API

### Para las canciones

- `GET /songs`: Obtener todas las canciones.
- `GET /songs/{id}`: Obtener una canción por su ID.
- `GET /songs/title/{title}`: Obtener canciones por su título.
- `GET /songs/artist/{name}`: Obtener canciones por nombre de artista.
- `POST /songs/save`: Crear una nueva canción.
- `PUT /songs/update/{id}`: Actualizar una canción por su ID.
- `DELETE /songs/delete/{id}`: Eliminar una canción por su ID.

### Para los artistas

- `GET /artists`: Obtener todos los artistas.
- `GET /artists/{id}`: Obtener un artista por su ID.
- `GET /artists/name/{name}`: Obtener un artista por su nombre.
- `POST /artists/save`: Agregar un nuevo artista.
- `PUT /artists/{id}`: Actualizar un artista por su ID.
- `DELETE /artists/delete/{id}`: Eliminar un artista por su ID.
### Para los álbumes

- `GET /albums`: Obtener todos los álbumes.
- `GET /albums/{id}`: Obtener un álbum por su ID.
- `GET /albums/title/{title}`: Obtener un álbum por su título.
- `POST /albums/save`: Agregar un nuevo álbum.
- `PUT /albums/update/{id}`: Actualizar un álbum por su ID.
- `DELETE /albums/{id}`: Eliminar un álbum por su ID.

---

## Tecnologías
- Java 22
- Spring Boot
- MongoDB
- Docker
- Maven
- Postman
- Git
- GitHub
- IntelliJ IDEA

---



## Contribuciones
Las contribuciones son bienvenidas. Para contribuir, sigue estos pasos:

### Haz un fork del repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza los cambios y haz commit (git commit -m 'Añadir nueva funcionalidad').
Envía tus cambios (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.
Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.