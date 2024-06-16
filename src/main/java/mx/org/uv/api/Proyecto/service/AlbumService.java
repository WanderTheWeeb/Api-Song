package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.model.Album;
import mx.org.uv.api.Proyecto.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> allAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(ObjectId id) {
        return albumRepository.findById(id);
    }

    public Optional<Album> getAlbumByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbum(ObjectId id) {
        albumRepository.deleteById(id);
    }
}
