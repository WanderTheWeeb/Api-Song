package mx.org.uv.api.Proyecto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa un artista musical")
public class ArtistDTO {

    @Schema(description = "ID único del artista", example = "60d5ec49c2a3543d2c1c73b1")
    private String id;

    @Schema(description = "Nombre del artista", example = "The Beatles")
    private String name;

    @Schema(description = "Género musical del artista", example = "Rock")
    private String genre;

    @Schema(description = "Biografía del artista", example = "Banda de rock británica formada en Liverpool en 1960")
    private String bio;

}
