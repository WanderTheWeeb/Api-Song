package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.error.exception.ArtistNotFoundException;
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

    public Optional<Artist> findByName(String name) {
        return artistRepository.findByName(name);
    }

    public Artist saveArtist(Artist artist) {
        Optional<Artist> existingArtist = artistRepository.findById(artist.getId());
        if (existingArtist.isPresent()) {
            throw new ArtistNotFoundException(artist.getId().toString());
        }
        return artistRepository.save(artist);
    }

    public Artist updateArtist(ObjectId id, Artist artist) {
        Optional<Artist> existingArtist = artistRepository.findById(id);
        if (existingArtist.isPresent()) {
            artist.setId(id);
            return artistRepository.save(artist);
        } else {
            throw new ArtistNotFoundException(id.toString());
        }
    }

    public boolean existsById(ObjectId id) {
        return artistRepository.existsById(id);
    }

    public void deleteArtist(ObjectId id) {
        artistRepository.deleteById(id);
    }
}
