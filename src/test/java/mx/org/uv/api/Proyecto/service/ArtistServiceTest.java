package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.error.exception.ArtistNotFoundException;
import mx.org.uv.api.Proyecto.model.Artist;
import mx.org.uv.api.Proyecto.repository.ArtistRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    private Artist artist;

    @BeforeEach
    void setUp() {

        artist = new Artist();
        artist.setId(new ObjectId("60c72b2f9b1e8a1a4c8d5678"));
        artist.setName("Cuco");
        artist.setGenre("Alternative/Indie");
        artist.setBio("Cuco es un artista conocido por su mezcla de ritmos suaves y letras sinceras.");
    }

    @Test
    void allArtist() {
        List<Artist> artists = new ArrayList<>();
        artists.add(artist);

        when(artistRepository.findAll()).thenReturn(artists);

        List<Artist> result = artistService.allArtist();

        assertEquals(1, result.size());
        assertEquals(artist.getName(), result.get(0).getName());
    }

    @Test
    void findByName_ExistingName_ReturnsArtist() {
        when(artistRepository.findByName(artist.getName())).thenReturn(Optional.of(artist));

        Optional<Artist> result = artistService.findByName(artist.getName());

        assertTrue(result.isPresent());
        assertEquals(artist.getId(), result.get().getId());
        assertEquals(artist.getGenre(), result.get().getGenre());
        assertEquals(artist.getBio(), result.get().getBio());
    }

    @Test
    void findByName_NonExistingName_ReturnsEmptyOptional() {
        String nonExistingName = "NonExistingName";
        when(artistRepository.findByName(nonExistingName)).thenReturn(Optional.empty());

        Optional<Artist> result = artistService.findByName(nonExistingName);

        assertFalse(result.isPresent());
    }

    @Test
    void saveArtist_AlreadyExistingArtist_ThrowsArtistNotFoundException() {
        when(artistRepository.findById(artist.getId())).thenReturn(Optional.of(artist));

        assertThrows(ArtistNotFoundException.class, () -> artistService.saveArtist(artist));
    }

    @Test
    void saveArtist_NewArtist_SavesArtist() {
        when(artistRepository.findById(artist.getId())).thenReturn(Optional.empty());
        when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.saveArtist(artist);

        assertEquals(artist.getId(), result.getId());
        assertEquals(artist.getName(), result.getName());
        assertEquals(artist.getGenre(), result.getGenre());
        assertEquals(artist.getBio(), result.getBio());
    }

    @Test
    void updateArtist_ExistingArtist_UpdatesArtist() {
        Artist updatedArtist = new Artist();
        updatedArtist.setId(artist.getId());
        updatedArtist.setName("UpdatedCuco");
        updatedArtist.setGenre("UpdatedGenre");
        updatedArtist.setBio("UpdatedBio");

        when(artistRepository.findById(artist.getId())).thenReturn(Optional.of(artist));
        when(artistRepository.save(updatedArtist)).thenReturn(updatedArtist);

        Artist result = artistService.updateArtist(artist.getId(), updatedArtist);

        assertEquals(updatedArtist.getId(), result.getId());
        assertEquals(updatedArtist.getName(), result.getName());
        assertEquals(updatedArtist.getGenre(), result.getGenre());
        assertEquals(updatedArtist.getBio(), result.getBio());
    }

    @Test
    void updateArtist_NonExistingArtist_ThrowsArtistNotFoundException() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        Artist updatedArtist = new Artist();
        updatedArtist.setId(nonExistingId);

        when(artistRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ArtistNotFoundException.class, () -> artistService.updateArtist(nonExistingId, updatedArtist));
    }

    @Test
    void existsById_ExistingId_ReturnsTrue() {
        when(artistRepository.existsById(artist.getId())).thenReturn(true);

        assertTrue(artistService.existsById(artist.getId()));
    }

    @Test
    void existsById_NonExistingId_ReturnsFalse() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        when(artistRepository.existsById(nonExistingId)).thenReturn(false);

        assertFalse(artistService.existsById(nonExistingId));
    }

    @Test
    void deleteArtist() {
        artistService.deleteArtist(artist.getId());

        verify(artistRepository, times(1)).deleteById(artist.getId());
    }
}
