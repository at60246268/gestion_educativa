package pe.edu.idat.gestion.app.ms.planillas.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.ApiResponse;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgNoValid(MethodArgumentNotValidException ex)
    {
        String detalle = ex.getBindingResult().getFieldErrors()
                .stream()
                .map( e->e.getField()+":"+e.getDefaultMessage())
                .collect(Collectors.joining(","));
        log.warn("Validación fallida: {}",detalle);
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Validación fallida: "+detalle));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex)
    {
        String detalle = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath()+":"+cv.getMessage())
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest()
                .body(ApiResponse.error("Parámetro inválido: "+detalle));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex)
    {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(BadCredentialsException ex)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Usuario o contraseña incorrectos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex)
    {
        log.error("Error interno no controlado", ex);
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error("Error interno en el servidor" + ex.getMessage()));
    }
}
