package mx.org.uv.api.Proyecto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Artists")
@Schema(description = "Entidad que representa un artista musical")
public class Artist {

    @Id
    @Schema(description = "ID único del artista", example = "60d5ec49c2a3543d2c1c73b1")
    private ObjectId id;

    @Schema(description = "Nombre del artista", example = "The Beatles")
    private String name;

    @Schema(description = "Género musical del artista", example = "Rock")
    private String genre;

    @Schema(description = "Biografía del artista", example = "Banda de rock británica formada en Liverpool en 1960")
    private String bio;
}
