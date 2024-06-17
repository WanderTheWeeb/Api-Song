package mx.org.uv.api.Proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todas las canciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canciones encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<Song> songs = songService.allSongs();
        return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
    }

    @Operation(summary = "Obtener una canción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canción encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Canción no encontrada",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable ObjectId id) {
        Optional<Song> song = songService.songById(id);
        SongDTO songDTO = song.map(songMapper::toSongDTO)
                .orElseThrow(() -> new SongNotFoundException(id.toString()));
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener canciones por título")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canciones encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Canciones no encontradas",
                    content = @Content) })
    @GetMapping("/title/{title}")
    public ResponseEntity<List<SongDTO>> getSongsByTitle(@PathVariable String title) {
        List<Song> songs = songService.songsByTitle(title);
        if (!songs.isEmpty()) {
            return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener canciones por nombre de artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canciones encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) })
    })
    @GetMapping("/artist/{name}")
    public ResponseEntity<List<SongDTO>> getSongsByArtistName(@PathVariable String name) {
        List<Song> songs = songService.songsByArtistName(name);
        return new ResponseEntity<>(songMapper.toSongDTOs(songs), HttpStatus.OK);
    }

    @Operation(summary = "Agregar una nueva canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Canción creada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content) })
    @PostMapping("/save")
    public ResponseEntity<SongDTO> saveSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        SongDTO songDTO = songMapper.toSongDTO(savedSong);
        return new ResponseEntity<>(songDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una canción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canción actualizada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID o datos inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Canción no encontrada",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable ObjectId id, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(id, song);
        SongDTO songDTO = songMapper.toSongDTO(updatedSong);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una canción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Canción eliminada"),
            @ApiResponse(responseCode = "404", description = "Canción no encontrada",
                    content = @Content) })
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
