package pe.edu.idat.gestion.app.ms.planillas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.gestion.app.ms.planillas.entity.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long>
{
    Optional<Rol> findByNombre(String nombre);
}
