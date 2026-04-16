package pe.edu.idat.gestion.app.ms.planillas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms.planillas.entity.Afp;

import java.util.List;

@Repository
public interface AfpRepository extends JpaRepository<Afp,Long>
{
    List<Afp> findByEstado(Integer estado);
}
