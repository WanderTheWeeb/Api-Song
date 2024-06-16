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
@Document(collection = "Songs")
public class Song {
    @Id
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
    @DocumentReference
    private List<Artist> collaborators;

}
