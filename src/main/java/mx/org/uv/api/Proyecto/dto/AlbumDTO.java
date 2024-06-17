package mx.org.uv.api.Proyecto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO que representa un álbum musical")
public class AlbumDTO {

    @Schema(description = "ID único del álbum", example = "60d5ec49c2a3543d2c1c73b1")
    private String id;

    @Schema(description = "Título del álbum", example = "Abbey Road")
    private String title;

    @Schema(description = "ID del artista", example = "60d5ec49c2a3543d2c1c73b2")
    private String artistId;

    @Schema(description = "Año de lanzamiento del álbum", example = "1969")
    private int releaseYear;

    @Schema(description = "Género del álbum", example = "Rock")
    private String genre;

    @Schema(description = "Descripción del álbum", example = "El último álbum de estudio de The Beatles")
    private String description;

    @Schema(description = "Lista de nombres de los artistas colaboradores en el álbum")
    private List<String> collaborators;

}
