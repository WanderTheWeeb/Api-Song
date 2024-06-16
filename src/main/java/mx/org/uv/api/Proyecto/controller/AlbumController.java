package mx.org.uv.api.Proyecto.controller;

import mx.org.uv.api.Proyecto.dto.AlbumDTO;
import mx.org.uv.api.Proyecto.mapper.AlbumMapper;
import mx.org.uv.api.Proyecto.model.Album;
import mx.org.uv.api.Proyecto.service.AlbumService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumMapper albumMapper;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<Album> albums = albumService.allAlbums();
        return new ResponseEntity<>(albumMapper.toAlbumDTOs(albums), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlbumDTO>> getAlbumById(@PathVariable ObjectId id) {
        Optional<Album> album = albumService.getAlbumById(id);
        Optional<AlbumDTO> albumDTO = album.map(albumMapper::toAlbumDTO);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Optional<AlbumDTO>> getAlbumByTitle(@PathVariable String title) {
        Optional<Album> album = albumService.getAlbumByTitle(title);
        Optional<AlbumDTO> albumDTO = album.map(albumMapper::toAlbumDTO);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AlbumDTO> saveAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.saveAlbum(album);
        AlbumDTO albumDTO = albumMapper.toAlbumDTO(savedAlbum);
        return new ResponseEntity<>(albumDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable ObjectId id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
