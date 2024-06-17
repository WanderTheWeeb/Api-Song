package mx.org.uv.api.Proyecto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO que representa una canción")
public class SongDTO {

    @Schema(description = "ID único de la canción", example = "60d5ec49c2a3543d2c1c73b2")
    private String id;

    @Schema(description = "Título de la canción", example = "Let It Be")
    private String title;

    @Schema(description = "ID del artista", example = "60d5ec49c2a3543d2c1c73b3")
    private String artistId;

    @Schema(description = "ID del álbum", example = "60d5ec49c2a3543d2c1c73b4")
    private String albumId;

    @Schema(description = "Género de la canción", example = "Rock")
    private String genre;

    @Schema(description = "Año de lanzamiento de la canción", example = "1970")
    private int year;

    @Schema(description = "Duración de la canción en formato mm:ss", example = "04:03")
    private String duration;

    @Schema(description = "Popularidad de la canción", example = "85")
    private int popularity;

    @Schema(description = "Descripción de la canción", example = "Una de las canciones más famosas de The Beatles")
    private String description;

    @Schema(description = "Letras de la canción", example = "When I find myself in times of trouble...")
    private String lyrics;

    @Schema(description = "Lista de nombres de los artistas colaboradores en la canción")
    private List<String> collaborators;

}
