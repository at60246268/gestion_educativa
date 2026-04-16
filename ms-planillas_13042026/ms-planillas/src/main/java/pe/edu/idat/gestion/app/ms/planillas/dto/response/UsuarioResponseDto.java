package pe.edu.idat.gestion.app.ms.planillas.dto.response;

import java.util.Set;

public record UsuarioResponseDto
        (
                Long idUsuario,
                String username,
                String password,
                String email,
                Integer estado,
                Set<String> roles

        )
{
}
