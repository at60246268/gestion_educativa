package pe.edu.idat.gestion.app.ms.planillas.mapper;

import org.mapstruct.*;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AreaRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AreaResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;
import pe.edu.idat.gestion.app.ms.planillas.entity.Area;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {



    @Mapping(target = "idArea", ignore = true)
    Area toEntity(AreaRequestDto areaRequestDto);

    AreaResponseDto toResponse(Area area);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idArea", ignore = true)
    void updateFromRequest(AreaRequestDto areaRequestDto, @MappingTarget Area area);

    List<AreaResponseDto> toResponseList(List<Area> areas);

}
