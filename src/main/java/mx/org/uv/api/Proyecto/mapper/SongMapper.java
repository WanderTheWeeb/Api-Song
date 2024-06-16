package mx.org.uv.api.Proyecto.mapper;

import mx.org.uv.api.Proyecto.dto.SongDTO;
import mx.org.uv.api.Proyecto.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SongMapper {

    SongDTO toSongDTO(Song song);
    Song toSong(SongDTO songDTO);

    List<SongDTO> toSongDTOs(List<Song> songs);
    List<Song> toSongs(List<SongDTO> songDTOs);

}
