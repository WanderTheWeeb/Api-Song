package mx.org.uv.api.Proyecto.controller;

import mx.org.uv.api.Proyecto.dto.AlbumDTO;
import mx.org.uv.api.Proyecto.error.exception.AlbumNotFoundException;
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
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable ObjectId id) {
        Optional<Album> album = albumService.getAlbumById(id);
        AlbumDTO albumDTO = album.map(albumMapper::toAlbumDTO).orElseThrow(() -> new AlbumNotFoundException(id.toString()));
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<AlbumDTO> getAlbumByTitle(@PathVariable String title) {
        Optional<Album> album = albumService.getAlbumByTitle(title);
        AlbumDTO albumDTO = album.map(albumMapper::toAlbumDTO).orElseThrow(() -> new AlbumNotFoundException(title));
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AlbumDTO> saveAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.saveAlbum(album);
        AlbumDTO albumDTO = albumMapper.toAlbumDTO(savedAlbum);
        return new ResponseEntity<>(albumDTO, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable ObjectId id, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(id, album);
        AlbumDTO albumDTO = albumMapper.toAlbumDTO(updatedAlbum);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable ObjectId id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
