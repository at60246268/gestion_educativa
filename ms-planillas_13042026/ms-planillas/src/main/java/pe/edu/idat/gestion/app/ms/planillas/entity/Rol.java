package pe.edu.idat.gestion.app.ms.planillas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tm_rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rol")
    @SequenceGenerator(name = "seq_rol", sequenceName = "seq_rol", allocationSize = 1)
    @Column(name = "nidrol")
    private Long idRol;

    @Column(name = "snombre",  nullable = false, unique = true, length = 30)
    private String nombre;

    @Column(name = "sdescripcion",  nullable = false, length = 50)
    private String descripcion;

    @Column(name = "nestado", nullable = false)
    private Integer estado;

    @PrePersist
    public void prePersist()
    {
        if(this.estado == null)
        {
            this.estado = 1;
        }
    }


}
