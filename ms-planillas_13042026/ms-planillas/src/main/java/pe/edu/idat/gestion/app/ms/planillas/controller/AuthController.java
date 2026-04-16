package pe.edu.idat.gestion.app.ms.planillas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.LoginRequest;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.LoginResponse;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.RegisterRequest;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.ApiResponse;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.service.impl.AuthServiceImpl;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController
{
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok(authService.login(loginRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok("Usuario registrado exitosamente", authService.register(registerRequest)));
    }


}
