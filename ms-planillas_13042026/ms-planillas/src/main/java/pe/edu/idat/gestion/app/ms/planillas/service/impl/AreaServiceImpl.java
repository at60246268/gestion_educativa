package pe.edu.idat.gestion.app.ms.planillas.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AreaRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AreaResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Area;
import pe.edu.idat.gestion.app.ms.planillas.mapper.AreaMapper;
import pe.edu.idat.gestion.app.ms.planillas.repository.AreaRepository;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService
{
    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;

    @Override
    @Transactional (readOnly = true)
    public List<AreaResponseDto> findAll() {
        return areaMapper.toResponseList(areaRepository.findAll());
    }

    @Override
    @Transactional (readOnly = true)
    public AreaResponseDto findById(Long idArea) {
        Area area = areaRepository.findById( idArea)
                .orElseThrow( () -> new RuntimeException("Error al buscar el área de trabajo"));
        return areaMapper.toResponse(area);
    }

    @Override
    @Transactional
    public AreaResponseDto create(AreaRequestDto areaRequestDto) {
        log.info("Create Área: {}", areaRequestDto);
        return areaMapper.toResponse(areaRepository.save(areaMapper.toEntity(areaRequestDto)));
    }

    @Override
    public AreaResponseDto update(Long idArea, AreaRequestDto areaRequestDto) {
        return null;
    }

    @Override
    public void delete(Long idArea) {

    }
}
