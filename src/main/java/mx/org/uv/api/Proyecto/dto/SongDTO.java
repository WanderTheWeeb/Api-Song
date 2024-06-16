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
public class SongDTO {
    private ObjectId id;
    private String title;
    private ObjectId artistId;
    private ObjectId albumId;
    private String genre;
    private int year;
    private String duration;
    private int popularity;
    private String description;
    private String lyrics;
    private List<Artist> collaborators;
}
