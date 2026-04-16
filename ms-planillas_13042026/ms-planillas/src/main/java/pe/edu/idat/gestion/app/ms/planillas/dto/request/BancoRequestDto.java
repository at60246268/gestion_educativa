package pe.edu.idat.gestion.app.ms.planillas.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BancoRequestDto
{
    @NotBlank(message = "El nombre del banco es obligatorio")
    @Size(max = 30, message = "El nombre del banco no debe superar los 30 caracteres")
    private String nombre;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(max = 16, message = "El número del banco no debe superar los 16 caracteres")
    private String numeroCuenta;

}
