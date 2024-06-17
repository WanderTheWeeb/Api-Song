package mx.org.uv.api.Proyecto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Songs")
@Schema(description = "Entidad que representa una canción")
public class Song {

    @Id
    @Schema(description = "ID único de la canción", example = "60d5ec49c2a3543d2c1c73b2")
    private ObjectId id;

    @Schema(description = "Título de la canción", example = "Let It Be")
    private String title;

    @Schema(description = "ID del artista", example = "60d5ec49c2a3543d2c1c73b3")
    private ObjectId artistId;

    @Schema(description = "ID del álbum", example = "60d5ec49c2a3543d2c1c73b4")
    private ObjectId albumId;

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

    @DocumentReference
    @Schema(description = "Lista de artistas colaboradores en la canción")
    private List<Artist> collaborators;
}
