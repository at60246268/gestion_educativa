package pe.edu.idat.gestion.app.ms.planillas.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.LoginRequest;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.LoginResponse;
import pe.edu.idat.gestion.app.ms.planillas.dto.auth.RegisterRequest;
import pe.edu.idat.gestion.app.ms.planillas.dto.response.UsuarioResponseDto;
import pe.edu.idat.gestion.app.ms.planillas.entity.Rol;
import pe.edu.idat.gestion.app.ms.planillas.entity.Usuario;
import pe.edu.idat.gestion.app.ms.planillas.repository.RolRepository;
import pe.edu.idat.gestion.app.ms.planillas.repository.UsuarioRepository;
import pe.edu.idat.gestion.app.ms.planillas.security.JwtService;
import pe.edu.idat.gestion.app.ms.planillas.security.UserDetailsImpl;
import pe.edu.idat.gestion.app.ms.planillas.security.UserDetailsServiceImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl
{
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;

    @Transactional
    public UsuarioResponseDto register(RegisterRequest registerRequest)
    {
        // Verificar si el usuario existe
        if(usuarioRepository.existsByUsername(registerRequest.username()))
        {
            throw new UsernameNotFoundException("El username no existe");
        }

       // Set<Rol> roles = definirRoles(Set<String> nombreRoles){
        Set<Rol> roles = definirRoles(registerRequest.roles());

        Usuario usuario = Usuario.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password())) // --> BCrypt
                .email(registerRequest.email())
                .roles(roles)
                .build();

        Usuario usuarioSave = usuarioRepository.save(usuario);
        log.info("Usuario registrado correctamente: {}",   usuarioSave.getIdUsuario());

        return toUsuarioResponse(usuarioSave);

    }

    private Set<Rol> definirRoles(Set<String> roles)
    {
        Set<Rol> roleSet = new HashSet<>();
        if(roles == null || roles.isEmpty())
        {
            roleSet.add(rolRepository.findByNombre("RRHH")
                    .orElseThrow(()-> new IllegalArgumentException("Rol no encontrado")));

        }
        else {
            for(String rolName : roles)
            {
                Rol r = rolRepository.findByNombre(rolName.toUpperCase())
                        .orElseThrow( () -> new IllegalArgumentException("Rol no encontrado"));
                roleSet.add(r);
            }
        }
        return roleSet;
    }

    private UsuarioResponseDto toUsuarioResponse(Usuario usuario)
    {
        Set<String> roles = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());
        return new UsuarioResponseDto(usuario.getIdUsuario(), usuario.getUsername(),usuario.getPassword(),
                usuario.getEmail(), usuario.getEstado(), roles);

    }

    //Login de autenticación
    public LoginResponse login(LoginRequest loginRequest)
    {
        //1.- Autentico credenciales , lanzo un badcredetianls
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        ));

        //2. Obtener datos del usuario autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.username());

        //3. Extaer los roles
        Set<String> roles = userDetails.getUsuario().getRoles()
                .stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());
        //4. Generar el JWT
        log.info("Login exitoso del usaurio: {} con roles {}", loginRequest.username(), roles);
        String token = jwtService.generateToken(userDetails);
        return LoginResponse.of(token, loginRequest.username(), roles, jwtService.getExpirationms());

    }

}
