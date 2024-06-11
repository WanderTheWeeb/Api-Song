package mx.org.uv.api.Proyecto.model;

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
public class Artist {
    @Id
    private ObjectId id;
    private String name;
    private String genre;
    private String bio;
}
