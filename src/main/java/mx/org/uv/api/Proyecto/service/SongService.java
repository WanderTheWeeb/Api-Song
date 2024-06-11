package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.model.Artist;
import mx.org.uv.api.Proyecto.model.Song;
import mx.org.uv.api.Proyecto.repository.SongRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;
    @Autowired
    ArtistService artistService;


    public List<Song> allSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> songById(ObjectId id) {
        return songRepository.findById(id);
    }


}
