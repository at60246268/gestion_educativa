package pe.edu.idat.gestion.app.ms.planillas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.idat.gestion.app.ms.planillas.entity.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>
{
    @Query("SELECT e FROM Empleado e WHERE UPPER(e.apellidoPaterno) LIKE UPPER(CONCAT('%', :apellidoPaterno, '%'))")
    List<Empleado> findByApellidoPaternoContaining(@Param("apellidoPaterno") String apellidoPaterno);
}
