package pe.edu.idat.gestion.app.ms.planillas.dto.response;

import lombok.*;

import java.math.BigDecimal;
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaResponseDto
{
    private Long idAfp;
    private String nombre;
    private String descripcion;
    private Integer estado;
}*/

public record AreaResponseDto(
        Long idArea,
        String nombre,
        String descripcion,
        Integer estado
) {
}
