package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.error.exception.SongAlreadyExistsException;
import mx.org.uv.api.Proyecto.error.exception.SongNotFoundException;
import mx.org.uv.api.Proyecto.model.Album;
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

    public List<Song> songsByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public List<Song> songsByArtistName(String artistName) {
        Optional<Artist> artist = artistService.findByName(artistName);
        if (artist.isPresent()) {
            return songRepository.findByArtistId(artist.get().getId());
        }
        return Collections.emptyList();
    }

    public Song saveSong(Song song) {
        Optional<Song> existingSong = songRepository.findById(song.getId());
        if (existingSong.isPresent()) {
            throw new SongAlreadyExistsException(song.getId().toString());
        }
        return songRepository.save(song);
    }

    public Song updateSong(ObjectId id, Song song) {
        Optional<Song> existingSong = songRepository.findById(id);
        if (existingSong.isPresent()) {
            song.setId(id);
            return songRepository.save(song);
        } else {
            throw new SongNotFoundException(id.toString());
        }
    }

    public void deleteSong(ObjectId id) {
        songRepository.deleteById(id);
    }
}
