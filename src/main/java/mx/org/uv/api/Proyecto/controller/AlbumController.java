package mx.org.uv.api.Proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todos los álbumes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Álbumes encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<Album> albums = albumService.allAlbums();
        return new ResponseEntity<>(albumMapper.toAlbumDTOs(albums), HttpStatus.OK);
    }

    @Operation(summary = "Obtener un álbum por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Álbum encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Álbum no encontrado",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable ObjectId id) {
        Optional<Album> album = albumService.getAlbumById(id);
        AlbumDTO albumDTO = album.map(albumMapper::toAlbumDTO).orElseThrow(() -> new AlbumNotFoundException(id.toString()));
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un álbum por su título")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Álbum encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Álbum no encontrado",
                    content = @Content) })
    @GetMapping("/title/{title}")
    public ResponseEntity<AlbumDTO> getAlbumByTitle(@PathVariable String title) {
        Optional<Album> album = albumService.getAlbumByTitle(title);
        AlbumDTO albumDTO = album.map(albumMapper::toAlbumDTO).orElseThrow(() -> new AlbumNotFoundException(title));
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @Operation(summary = "Agregar un nuevo álbum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Álbum creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content) })
    @PostMapping("/save")
    public ResponseEntity<AlbumDTO> saveAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.saveAlbum(album);
        AlbumDTO albumDTO = albumMapper.toAlbumDTO(savedAlbum);
        return new ResponseEntity<>(albumDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un álbum por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Álbum actualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlbumDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID o datos inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Álbum no encontrado",
                    content = @Content) })
    @PutMapping("update/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable ObjectId id, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(id, album);
        AlbumDTO albumDTO = albumMapper.toAlbumDTO(updatedAlbum);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un álbum por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Álbum eliminado"),
            @ApiResponse(responseCode = "404", description = "Álbum no encontrado",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable ObjectId id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
