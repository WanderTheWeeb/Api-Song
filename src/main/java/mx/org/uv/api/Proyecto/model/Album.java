package mx.org.uv.api.Proyecto.model;

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
public class Album {
    @Id
    private ObjectId id;
    private String title;
    private String artistId;
    private int releaseYear;
    private String genre;
    private String description;
    @DocumentReference
    private List<Artist> collaborators;
}
