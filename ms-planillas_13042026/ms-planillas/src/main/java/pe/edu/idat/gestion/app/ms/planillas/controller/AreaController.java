package pe.edu.idat.gestion.app.ms.planillas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AreaRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.ApiResponse;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AreaResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.service.impl.AfpService;
import pe.edu.idat.gestion.app.ms.planillas.service.impl.AreaService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/areas")
public class AreaController
{
    private final AreaService areaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AreaResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( areaService.findAll() ));
    }

    @GetMapping("/{idArea}")
    public ResponseEntity< ApiResponse<AreaResponseDto> > findById(@PathVariable Long idArea)
    {
        return ResponseEntity.ok(ApiResponse.ok(areaService.findById(idArea)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AreaResponseDto>> create(@Valid @RequestBody AreaRequestDto areaRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el área de trabajo",   areaService.create(areaRequestDto)));
    }

}
