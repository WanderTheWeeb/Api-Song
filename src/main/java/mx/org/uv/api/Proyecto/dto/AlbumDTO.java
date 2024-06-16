package mx.org.uv.api.Proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.org.uv.api.Proyecto.model.Artist;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDTO {
    private ObjectId id;
    private String title;
    private ObjectId artistId;
    private int releaseYear;
    private String genre;
    private String description;
    private List<Artist> collaborators;
}
