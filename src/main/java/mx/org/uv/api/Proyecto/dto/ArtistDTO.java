package mx.org.uv.api.Proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDTO {
    private ObjectId id;
    private String name;
    private String genre;
    private String bio;
}
