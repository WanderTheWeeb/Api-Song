package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.model.Artist;
import mx.org.uv.api.Proyecto.repository.ArtistReporitory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistReporitory artistRepository;

    public List<Artist> allArtist() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(ObjectId id) {
        return artistRepository.findById(id);
    }



}
