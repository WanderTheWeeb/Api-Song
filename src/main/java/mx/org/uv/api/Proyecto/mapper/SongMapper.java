package mx.org.uv.api.Proyecto.mapper;

import mx.org.uv.api.Proyecto.dto.SongDTO;
import mx.org.uv.api.Proyecto.model.Song;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SongMapper {

    SongDTO toSongDTO(Song song);
    @InheritInverseConfiguration
    Song toSong(SongDTO songDTO);

    List<SongDTO> toSongDTOs(List<Song> songs);
    List<Song> toSongs(List<SongDTO> songDTOs);

}
