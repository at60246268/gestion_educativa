package pe.edu.idat.gestion.app.ms.planillas.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciales de acceso")
public record LoginRequest (

        @NotBlank(message = "El usuario es obligatorio")
        @Schema(example = "admin")
        String username,

        @NotBlank(message = "La clave es obligatorio")
        @Schema(example = "xxxxx")
        String password
)
{}
