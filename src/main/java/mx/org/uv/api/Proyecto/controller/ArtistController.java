package mx.org.uv.api.Proyecto.controller;

import mx.org.uv.api.Proyecto.model.Artist;
import mx.org.uv.api.Proyecto.model.Song;
import mx.org.uv.api.Proyecto.service.ArtistService;
import mx.org.uv.api.Proyecto.service.SongService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllSongs() {
        return new ResponseEntity<>(artistService.allArtist(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Artist>> getArtistById(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Artist>>(artistService.getArtistById(id), HttpStatus.OK);
    }
}
