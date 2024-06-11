package mx.org.uv.api.Proyecto.repository;

import mx.org.uv.api.Proyecto.model.Artist;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistReporitory extends MongoRepository<Artist, ObjectId> {
}
