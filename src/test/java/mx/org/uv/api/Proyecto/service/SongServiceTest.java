package mx.org.uv.api.Proyecto.service;

import mx.org.uv.api.Proyecto.error.exception.SongNotFoundException;
import mx.org.uv.api.Proyecto.model.Song;
import mx.org.uv.api.Proyecto.repository.SongRepository;
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
class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    private Song song;

    @BeforeEach
    void setUp() {

        song = new Song();
        song.setId(new ObjectId("60c72b2f9b1e8a1a4c8d1234"));
        song.setTitle("Lo Que Siento");
        song.setArtistId(new ObjectId("60c72b2f9b1e8a1a4c8d5678"));
        song.setAlbumId(null);
        song.setGenre("Alternative/Indie");
        song.setYear(2017);
        song.setDuration("03:44");
        song.setPopularity(85);
        song.setDescription("Single de Cuco, conocido por su mezcla de ritmos suaves y letras sinceras.");
        song.setLyrics("Oh, oh... Dreaming of you when I'm alone.");
        song.setCollaborators(new ArrayList<>());
    }

    @Test
    void allSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        when(songRepository.findAll()).thenReturn(songs);

        List<Song> result = songService.allSongs();

        assertEquals(1, result.size());
        assertEquals(song.getTitle(), result.get(0).getTitle());
    }

    @Test
    void songById_ExistingId_ReturnsSong() {
        when(songRepository.findById(song.getId())).thenReturn(Optional.of(song));

        Optional<Song> result = songService.songById(song.getId());

        assertTrue(result.isPresent());
        assertEquals(song.getArtistId(), result.get().getArtistId());
        assertEquals(song.getGenre(), result.get().getGenre());
    }

    @Test
    void songById_NonExistingId_ReturnsEmptyOptional() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        when(songRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Optional<Song> result = songService.songById(nonExistingId);

        assertFalse(result.isPresent());
    }

    @Test
    void songsByTitle_ExistingTitle_ReturnsSongList() {
        when(songRepository.findByTitle(song.getTitle())).thenReturn(List.of(song));

        List<Song> result = songService.songsByTitle(song.getTitle());

        assertEquals(1, result.size());
        assertEquals(song.getArtistId(), result.get(0).getArtistId());
        assertEquals(song.getGenre(), result.get(0).getGenre());
    }

    @Test
    void songsByTitle_NonExistingTitle_ReturnsEmptyList() {
        String nonExistingTitle = "NonExistingTitle";
        when(songRepository.findByTitle(nonExistingTitle)).thenReturn(List.of());

        List<Song> result = songService.songsByTitle(nonExistingTitle);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveSong() {
        when(songRepository.save(song)).thenReturn(song);

        Song result = songService.saveSong(song);

        assertEquals(song.getId(), result.getId());
        assertEquals(song.getTitle(), result.getTitle());
        assertEquals(song.getGenre(), result.getGenre());
    }

    @Test
    void updateSong_ExistingSong_UpdatesSong() {
        Song updatedSong = new Song();
        updatedSong.setId(song.getId());
        updatedSong.setTitle("Updated Lo Que Siento");
        updatedSong.setYear(2020);

        when(songRepository.findById(song.getId())).thenReturn(Optional.of(song));
        when(songRepository.save(updatedSong)).thenReturn(updatedSong);

        Song result = songService.updateSong(song.getId(), updatedSong);

        assertEquals(updatedSong.getId(), result.getId());
        assertEquals(updatedSong.getTitle(), result.getTitle());
        assertEquals(updatedSong.getYear(), result.getYear());
    }

    @Test
    void updateSong_NonExistingSong_ThrowsSongNotFoundException() {
        ObjectId nonExistingId = new ObjectId("60c72b2f9b1e8a1a4c8d9999");
        Song updatedSong = new Song();
        updatedSong.setId(nonExistingId);

        when(songRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(SongNotFoundException.class, () -> songService.updateSong(nonExistingId, updatedSong));
    }

    @Test
    void deleteSong() {
        songService.deleteSong(song.getId());

        verify(songRepository, times(1)).deleteById(song.getId());
    }
}
