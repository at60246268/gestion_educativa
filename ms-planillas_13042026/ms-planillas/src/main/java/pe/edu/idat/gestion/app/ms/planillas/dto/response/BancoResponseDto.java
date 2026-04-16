package pe.edu.idat.gestion.app.ms.planillas.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BancoResponseDto
{
    private Long idAfp;
    private String nombre;
    private String numeroCuenta;
    private Integer estado;
}
