package pe.edu.idat.gestion.app.ms.planillas.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;
import pe.edu.idat.gestion.app.ms.planillas.mapper.AfpMapper;
import pe.edu.idat.gestion.app.ms.planillas.repository.AfpRepository;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class AfpServiceImpl implements AfpService
{

    private final AfpMapper afpMapper;
    private final AfpRepository afpRepository;

    public AfpServiceImpl(AfpMapper afpMapper, AfpRepository afpRepository)
    {
        this.afpMapper = afpMapper;
        this.afpRepository = afpRepository;
    }

    @Override
    @Transactional
    public List<AfpResponseDto> findAll() {
        return afpMapper.toResponseList(afpRepository.findAll());
    }

    @Override
    @Transactional (readOnly = true)
    public AfpResponseDto findById(Long idAfp) {
        Afp afp = afpRepository.findById( Long.parseLong(idAfp.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar la AFP"));
        return afpMapper.toResponse(afp);
    }

    @Override
    @Transactional
    public AfpResponseDto create(AfpRequestDto afpRequestDto) {
        log.info("Create AFP: {}", afpRequestDto);
        return afpMapper.toResponse(afpRepository.save(afpMapper.toEntity(afpRequestDto)));
    }

    @Override
    @Transactional
    public AfpResponseDto update(Long idAfp, AfpRequestDto afpRequestDto) {
        Afp afp = afpRepository.findById( Long.parseLong(idAfp.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar la AFP"));
        log.info("Update AFP: {}", afpRequestDto);
        afpMapper.updateFromRequest(afpRequestDto, afp);
        return afpMapper.toResponse(afpRepository.save(afp));
    }

    @Override
    @Transactional
    public void delete(Long idAfp) {
        log.info("Delete AFP: {}", idAfp);
        if(!afpRepository.existsById( Long.parseLong(idAfp.toString())))
        {
            throw new RuntimeException("No existe el id de la AFP");
        }
        afpRepository.deleteById( Long.parseLong(idAfp.toString()));

    }
}
