package mx.org.uv.api.Proyecto.mapper;

import mx.org.uv.api.Proyecto.dto.AlbumDTO;
import mx.org.uv.api.Proyecto.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlbumMapper {
    AlbumDTO toAlbumDTO(Album album);
    Album toAlbum(AlbumDTO albumDTO);
    List<AlbumDTO> toAlbumDTOs(List<Album> albums);
    List<Album> toAlbums(List<AlbumDTO> albumDTOs);
}
