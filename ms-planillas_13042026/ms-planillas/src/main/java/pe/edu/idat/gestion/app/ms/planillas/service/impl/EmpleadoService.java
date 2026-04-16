package pe.edu.idat.gestion.app.ms.planillas.service.impl;

import pe.edu.idat.gestion.app.ms.planillas.dto.request.AreaRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.EmpleadoRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AreaResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.EmpleadoResponseDto;

import java.util.List;

public interface EmpleadoService
{
    List<EmpleadoResponseDto> findAll();
    EmpleadoResponseDto findById(Long idEmpleado);
    EmpleadoResponseDto create(EmpleadoRequestDto empleadoRequestDto);
    EmpleadoResponseDto update(Long idArea,EmpleadoRequestDto empleadoRequestDto);
    void delete(Long idEmpleado);
}
