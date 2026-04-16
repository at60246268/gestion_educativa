package pe.edu.idat.gestion.app.ms.planillas.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfpRequestDto
{
    @NotBlank(message = "El nombre de la AFP es obligatorio")
    @Size(max = 30, message = "El nombre de la AFP no debe superar los 30 caracteres")
    private String nombre;

    @NotNull(message = "La comisión fija es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "La comisión fija no puede ser negativa")
    private BigDecimal comisionFija;

    @NotNull(message = "La comisión variable es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "La comisión variable no puede ser negativa")
    private BigDecimal comisionVariable;

    @NotNull(message = "El rendimiento de la afp es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "El rendimiento no puede ser negativa")
    private BigDecimal rendimiento;
}
