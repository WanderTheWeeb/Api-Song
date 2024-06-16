package mx.org.uv.api.Proyecto.controller;

import mx.org.uv.api.Proyecto.dto.SongDTO;
import mx.org.uv.api.Proyecto.error.exception.SongNotFoundException;
import mx.org.uv.api.Proyecto.mapper.SongMapper;
import mx.org.uv.api.Proyecto.model.Song;
import mx.org.uv.api.Proyecto.service.SongService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private SongMapper songMapper;

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<Song> songs = songService.allSongs();
        return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable ObjectId id) {
        Optional<Song> song = songService.songById(id);
        SongDTO songDTO = song.map(songMapper::toSongDTO)
                .orElseThrow(() -> new SongNotFoundException(id.toString()));
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<SongDTO>> getSongsByTitle(@PathVariable String title) {
        List<Song> songs = songService.songsByTitle(title);
        if (!songs.isEmpty()) {
            return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/artist/{name}")
    public ResponseEntity<List<SongDTO>> getSongsByArtistName(@PathVariable String name) {
        List<Song> songs = songService.songsByArtistName(name);
        return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<SongDTO> saveSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        SongDTO songDTO = songMapper.toSongDTO(savedSong);
        return new ResponseEntity<>(songDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable ObjectId id, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(id, song);
        SongDTO songDTO = songMapper.toSongDTO(updatedSong);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable ObjectId id) {
        try {
            songService.deleteSong(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SongNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
