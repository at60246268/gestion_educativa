package pe.edu.idat.gestion.app.ms.planillas.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.EmpleadoRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.EmpleadoResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;
import pe.edu.idat.gestion.app.ms.planillas.entity.Area;
import pe.edu.idat.gestion.app.ms.planillas.entity.Empleado;
import pe.edu.idat.gestion.app.ms.planillas.mapper.EmpleadoMapper;
import pe.edu.idat.gestion.app.ms.planillas.repository.AfpRepository;
import pe.edu.idat.gestion.app.ms.planillas.repository.AreaRepository;
import pe.edu.idat.gestion.app.ms.planillas.repository.EmpleadoRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService
{
    private final EmpleadoRepository empleadoRepository;
    private final AfpRepository afpRepository;
    private final AreaRepository areaRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoResponseDto> findAll() {
        return empleadoMapper.toResponseList(empleadoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoResponseDto findById(Long idEmpleado) {
        return empleadoMapper.toResponse(
                empleadoRepository.findById(idEmpleado)
                        .orElseThrow(() -> new RuntimeException("No existe el empleado con el id:" + idEmpleado)));
    }

    @Override
    @Transactional
    public EmpleadoResponseDto create(EmpleadoRequestDto empleadoRequestDto) {
        Afp afp = afpRepository.findById( empleadoRequestDto.getIdAfp())
                .orElseThrow( () -> new RuntimeException("Error al buscar la AFP"));

        Area area = areaRepository.findById( empleadoRequestDto.getIdArea())
                .orElseThrow( () -> new RuntimeException("Error al buscar el área de trabajo"));

        Empleado empleado = empleadoMapper.toEntity(empleadoRequestDto);
        empleado.setAfp(afp);
        empleado.setArea(area);

        log.info("Creando el empleado: {} {}", empleadoRequestDto.getDni(), empleadoRequestDto.getIdArea());

        return empleadoMapper.toResponse(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoResponseDto update(Long idArea, EmpleadoRequestDto empleadoRequestDto) {
        return null;
    }

    @Override
    public void delete(Long idEmpleado) {

    }
}
