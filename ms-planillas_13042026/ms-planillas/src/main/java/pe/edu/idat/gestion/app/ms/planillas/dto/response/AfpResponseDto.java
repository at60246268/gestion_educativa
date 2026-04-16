package pe.edu.idat.gestion.app.ms.planillas.dto.response;

import lombok.*;

import java.math.BigDecimal;

/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder*/

public record AfpResponseDto(
        Long idAfp,
        String nombre,
        BigDecimal comisionFija,
        BigDecimal comisionVariable,
        BigDecimal rendimiento,
        Integer estado
)
{}

    /*
    private Long idAfp;
    private String nombre;
    private BigDecimal comisionFija;
    private BigDecimal comisionVariable;
    private BigDecimal rendimiento;
    private Integer estado;*/


