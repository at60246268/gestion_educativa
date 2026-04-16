package pe.edu.idat.gestion.app.ms.planillas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms.planillas.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long> {

}
