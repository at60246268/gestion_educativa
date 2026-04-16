package pe.edu.idat.gestion.app.ms.planillas.service.impl;


import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;

import java.util.List;

public interface AfpService
{
    List<AfpResponseDto> findAll();
    AfpResponseDto findById(Long idAfp);
    AfpResponseDto create(AfpRequestDto  afpRequestDto);
    AfpResponseDto update(Long idAfp,AfpRequestDto afpRequestDto);
    void delete(Long idAfp);

}
