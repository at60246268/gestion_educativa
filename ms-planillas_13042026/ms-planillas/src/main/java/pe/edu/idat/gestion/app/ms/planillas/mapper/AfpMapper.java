package pe.edu.idat.gestion.app.ms.planillas.mapper;

import org.mapstruct.*;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AfpMapper
{
    @Mapping(target = "idAfp", ignore = true)
    Afp toEntity(AfpRequestDto afpRequestDto);

    AfpResponseDto toResponse(Afp afp);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idAfp", ignore = true)
    void updateFromRequest(AfpRequestDto afpRequestDto, @MappingTarget Afp afp);

    List<AfpResponseDto> toResponseList(List<Afp> afps);

}
