package pe.edu.idat.gestion.app.ms.planillas.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.idat.gestion.app.ms.planillas.repository.UsuarioRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Iniciando usuario com login {}", username);
        return userRepository.findByUsername(username)
                .map(UserDetailsImpl::new) //Optional<UserDetailsImpl>
                .orElseThrow( () -> new UsernameNotFoundException(
                        "El usuario ingresado no existe: "+username
                ));
    }
}
