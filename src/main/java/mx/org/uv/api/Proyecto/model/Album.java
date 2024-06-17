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
@Document(collection = "Albums")
@Schema(description = "Entidad que representa un álbum musical")
public class Album {

    @Id
    @Schema(description = "ID único del álbum", example = "60d5ec49c2a3543d2c1c73b1")
    private ObjectId id;

    @Schema(description = "Título del álbum", example = "Abbey Road")
    private String title;

    @Schema(description = "ID del artista", example = "60d5ec49c2a3543d2c1c73b2")
    private ObjectId artistId;

    @Schema(description = "Año de lanzamiento del álbum", example = "1969")
    private int releaseYear;

    @Schema(description = "Género del álbum", example = "Rock")
    private String genre;

    @Schema(description = "Descripción del álbum", example = "El último álbum de estudio de The Beatles")
    private String description;

    @DocumentReference
    @Schema(description = "Lista de artistas colaboradores en el álbum")
    private List<Artist> collaborators;
}
