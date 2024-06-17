package mx.org.uv.api.Proyecto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.org.uv.api.Proyecto.dto.ArtistDTO;
import mx.org.uv.api.Proyecto.error.exception.ArtistNotFoundException;
import mx.org.uv.api.Proyecto.mapper.ArtistMapper;
import mx.org.uv.api.Proyecto.model.Artist;
import mx.org.uv.api.Proyecto.service.ArtistService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistMapper artistMapper;

    @Operation(summary = "Obtener todos los artistas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artistas encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllSongs() {
        List<Artist> artists = artistService.allArtist();
        return new ResponseEntity<>(artistMapper.toArtistDTOs(artists), HttpStatus.OK);
    }

    @Operation(summary = "Obtener un artista por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artista encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado",
                    content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable ObjectId id) {
        Optional<Artist> artist = artistService.getArtistById(id);
        ArtistDTO artistDTO = artist.map(artistMapper::toArtistDTO).orElseThrow(() -> new ArtistNotFoundException(id.toString()));
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un artista por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artista encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado",
                    content = @Content) })
    @GetMapping("/name/{name}")
    public ResponseEntity<ArtistDTO> getArtistByName(@PathVariable String name) {
        Optional<Artist> artist = artistService.findByName(name);
        ArtistDTO artistDTO = artist.map(artistMapper::toArtistDTO).orElseThrow(() -> new ArtistNotFoundException(name));
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @Operation(summary = "Agregar un nuevo artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artista creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content) })
    @PostMapping("/save")
    public ResponseEntity<ArtistDTO> saveArtist(@Validated @RequestBody Artist artist) {
        Artist savedArtist = artistService.saveArtist(artist);
        ArtistDTO artistDTO = artistMapper.toArtistDTO(savedArtist);
        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un artista por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artista actualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ID o datos inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado",
                    content = @Content) })
    @PutMapping("{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable ObjectId id, @RequestBody Artist artist) {
        Artist updatedArtist = artistService.updateArtist(id, artist);
        if (updatedArtist == null) {
            throw new ArtistNotFoundException(id.toString());
        }
        ArtistDTO artistDTO = artistMapper.toArtistDTO(updatedArtist);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un artista por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Artista eliminado"),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado",
                    content = @Content) })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable ObjectId id) {
        boolean exists = artistService.existsById(id);
        if (!exists) {
            throw new ArtistNotFoundException(id.toString());
        }
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
