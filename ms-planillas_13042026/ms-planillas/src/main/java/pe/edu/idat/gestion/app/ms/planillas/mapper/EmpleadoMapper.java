package pe.edu.idat.gestion.app.ms.planillas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.EmpleadoRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.EmpleadoResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Empleado;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {



    @Mapping(target = "idAfp", source = "afp.idAfp")
    @Mapping(target = "idArea", source = "area.idArea")
    EmpleadoResponseDto toResponse(Empleado empleado);


    @Mapping(target = "idEmpleado", ignore = true)
    @Mapping(target = "afp", ignore = true)
    @Mapping(target = "area", ignore = true)
    Empleado toEntity(EmpleadoRequestDto empleadoRequestDto);

    List<EmpleadoResponseDto> toResponseList(List<Empleado> empleados);
}
