package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.error.exception.AlbumAlreadyExistsException;
import mx.org.uv.api.Proyecto.error.exception.AlbumNotFoundException;
import mx.org.uv.api.Proyecto.model.Album;
import mx.org.uv.api.Proyecto.repository.AlbumRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    private Album album;

    @BeforeEach
    void setUp() {

        album = new Album();
        album.setId(new ObjectId("60c72b2f9b1e8a1a4c8d9102"));
        album.setTitle("Songs4u");
        album.setArtistId(new ObjectId("60c72b2f9b1e8a1a4c8d5678"));
        album.setReleaseYear(2017);
        album.setGenre("Alternative/Indie");
        album.setDescription("Álbum de Cuco lanzado en 2017.");
        album.setCollaborators(new ArrayList<>());
    }

    @Test
    void getAlbumById() {
        when(albumRepository.findById(album.getId())).thenReturn(Optional.of(album));

        Optional<Album> result = albumService.getAlbumById(album.getId());

        assertTrue(result.isPresent());
        assertEquals(album.getTitle(), result.get().getTitle());
        assertEquals(album.getGenre(), result.get().getGenre());
    }

    @Test
    void getAlbumByTitle() {
        when(albumRepository.findByTitle("Songs4u")).thenReturn(Optional.of(album));

        Optional<Album> result = albumService.getAlbumByTitle("Songs4u");

        assertTrue(result.isPresent());
        assertEquals(album.getTitle(), result.get().getTitle());
        assertEquals(album.getGenre(), result.get().getGenre());
    }

    @Test
    void saveAlbum() {
        when(albumRepository.save(album)).thenReturn(album);

        Album result = albumService.saveAlbum(album);

        assertNotNull(result);
        assertEquals(album.getTitle(), result.getTitle());
        assertEquals(album.getGenre(), result.getGenre());
    }

    @Test
    void updateAlbum() {
        Album updatedAlbum = new Album();
        updatedAlbum.setId(album.getId());
        updatedAlbum.setTitle("Updated Songs4u");
        updatedAlbum.setReleaseYear(2020);

        when(albumRepository.findById(album.getId())).thenReturn(Optional.of(album));
        when(albumRepository.save(updatedAlbum)).thenReturn(updatedAlbum);

        Album result = albumService.updateAlbum(album.getId(), updatedAlbum);

        assertNotNull(result);
        assertEquals(updatedAlbum.getTitle(), result.getTitle());
        assertEquals(updatedAlbum.getReleaseYear(), result.getReleaseYear());
    }

    @Test
    void deleteAlbum() {

        albumService.deleteAlbum(album.getId());

        verify(albumRepository, times(1)).deleteById(album.getId());
    }

    @Test
    void getAlbumById_NonExistingId_ReturnsEmptyOptional() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        when(albumRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Optional<Album> result = albumService.getAlbumById(nonExistingId);

        assertTrue(result.isEmpty());
    }

    @Test
    void updateAlbum_NonExistingId_ThrowsAlbumNotFoundException() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        Album updatedAlbum = new Album();
        updatedAlbum.setId(nonExistingId);

        when(albumRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(AlbumNotFoundException.class, () -> albumService.updateAlbum(nonExistingId, updatedAlbum));
    }

    @Test
    void saveAlbum_AlreadyExistingId_ThrowsAlbumAlreadyExistsException() {
        when(albumRepository.findById(album.getId())).thenReturn(Optional.of(album));
        assertThrows(AlbumAlreadyExistsException.class, () -> albumService.saveAlbum(album));
    }
}
