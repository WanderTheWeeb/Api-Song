package mx.org.uv.api.Proyecto.controller;

import mx.org.uv.api.Proyecto.dto.ArtistDTO;
import mx.org.uv.api.Proyecto.mapper.ArtistMapper;
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
    @Autowired
    private ArtistMapper artistMapper;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllSongs() {
        List<Artist> artists = artistService.allArtist();
        return new ResponseEntity<>(artistMapper.toArtistDTOs(artists), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ArtistDTO>> getArtistById(@PathVariable ObjectId id) {
        Optional<Artist> artist = artistService.getArtistById(id);
        Optional<ArtistDTO> artistDTO = artist.map(artistMapper::toArtistDTO);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }
}
