package pe.edu.idat.gestion.app.ms.planillas.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;
import pe.edu.idat.gestion.app.ms.planillas.entity.Area;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoRequestDto
{
    @NotBlank(message = "El dni es obligatorio")
    @Size(max = 8, min = 8, message = "El dni debe ser de 8 dígitos.")
    private String dni;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private BigDecimal salario;

    private String nacionalidad;

    private Long idAfp;

    private Long idArea;
}
