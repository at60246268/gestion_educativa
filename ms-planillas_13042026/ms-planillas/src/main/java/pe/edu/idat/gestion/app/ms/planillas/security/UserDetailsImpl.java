package pe.edu.idat.gestion.app.ms.planillas.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.idat.gestion.app.ms.planillas.entity.Usuario;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails
{
    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  usuario.getRoles()
                .stream()
                //ROLE_ADMIN | ROLE_USER | ROLE_RRHH --> debe haber una compatibilidad --> ADMIN
                .map( rol -> new SimpleGrantedAuthority("ROLE_"+rol.getNombre()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstado() == 1;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
