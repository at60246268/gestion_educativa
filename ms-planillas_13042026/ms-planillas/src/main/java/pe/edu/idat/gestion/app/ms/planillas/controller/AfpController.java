package pe.edu.idat.gestion.app.ms.planillas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.gestion.app.ms.planillas.dto.request.AfpRequestDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.AfpResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.ApiResponse;
import pe.edu.idat.gestion.app.ms.planillas.service.impl.AfpService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/afps")
public class AfpController
{
    private final AfpService afpService;

   /* @GetMapping
    public ResponseEntity<List<AfpResponseDto>> findAll()
    {
        return ResponseEntity.ok(afpService.findAll());
    }*/
   @GetMapping
   public ResponseEntity<ApiResponse<List<AfpResponseDto>>> findAll()
   {
       return ResponseEntity.ok(ApiResponse.ok( afpService.findAll() ));
   }

    @GetMapping("/{idAfp}")
    public ResponseEntity< ApiResponse<AfpResponseDto> > findById(@PathVariable Long idAfp)
    {
        return ResponseEntity.ok(ApiResponse.ok(afpService.findById(idAfp)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AfpResponseDto>> create(@Valid @RequestBody AfpRequestDto afpRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body( ApiResponse.ok("Se ha creado la AFP",   afpService.create(afpRequestDto)));
    }

}
