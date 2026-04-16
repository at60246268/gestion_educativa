package pe.edu.idat.gestion.app.ms.planillas.service.impl;

import pe.edu.idat.gestion.app.ms.planillas.dto.request.AreaRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AreaResponseDto;

import java.util.List;

public interface AreaService {

    List<AreaResponseDto> findAll();
    AreaResponseDto findById(Long idArea);
    AreaResponseDto create(AreaRequestDto areaRequestDto);
    AreaResponseDto update(Long idArea,AreaRequestDto areaRequestDto);
    void delete(Long idArea);

}
