package pe.edu.idat.gestion.app.ms.planillas.dto.response;

import lombok.*;

import java.math.BigDecimal;
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoResponseDto {

    private Long idEmpleado;

    private String dni;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private BigDecimal salario;

    private String nacionalidad;

    private Long idAfp;

    private Long idArea;
}
 */
public record EmpleadoResponseDto (

    Long idEmpleado,

    String dni,

    String nombres,

    String apellidoPaterno,

    String apellidoMaterno,

    BigDecimal salario,

    String nacionalidad,

    Long idAfp,

    Long idArea
)
{
}