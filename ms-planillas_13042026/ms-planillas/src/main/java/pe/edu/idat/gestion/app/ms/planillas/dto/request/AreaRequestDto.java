package pe.edu.idat.gestion.app.ms.planillas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaRequestDto
{
    @NotBlank(message = "El nombre del área de trabajo es obligatorio")
    @Size(max = 30, message = "El nombre del área no debe superar los 30 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 20, message = "El número de cuenta debe tener máximo 20 caracteres")
    private String descripcion;


}
