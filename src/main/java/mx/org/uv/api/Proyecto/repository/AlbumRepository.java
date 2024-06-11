package mx.org.uv.api.Proyecto.repository;

import mx.org.uv.api.Proyecto.model.Album;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, ObjectId> {
}
