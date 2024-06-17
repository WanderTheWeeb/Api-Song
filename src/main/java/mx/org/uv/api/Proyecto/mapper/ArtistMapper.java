package mx.org.uv.api.Proyecto.mapper;

import mx.org.uv.api.Proyecto.dto.ArtistDTO;
import mx.org.uv.api.Proyecto.model.Artist;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArtistMapper {
    ArtistDTO toArtistDTO(Artist artist);
    @InheritInverseConfiguration
    Artist toArtist(ArtistDTO artistDTO);
    List<ArtistDTO> toArtistDTOs(List<Artist> artists);
    List<Artist> toArtists(List<ArtistDTO> artistDTOs);
}
