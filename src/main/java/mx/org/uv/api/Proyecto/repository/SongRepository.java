package mx.org.uv.api.Proyecto.repository;

import mx.org.uv.api.Proyecto.model.Song;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends MongoRepository<Song, ObjectId> {
    List<Song> findByTitle(String title);
    List<Song> findByArtistId(ObjectId artistId);

}
