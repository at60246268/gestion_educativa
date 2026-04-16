package pe.edu.idat.gestion.app.ms.planillas.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tm_afp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Afp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_afp")
    @SequenceGenerator(name = "seq_afp", sequenceName = "seq_afp", allocationSize = 1)
    @Column(name = "nidafp")
    private Long idAfp;

    @Column(name = "snombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "ncomisionfija", precision = 20, scale = 2, nullable = false)
    private BigDecimal comisionFija;

    @Column(name = "ncomisionvariable", precision = 20, scale = 2, nullable = false)
    private BigDecimal comisionVariable;

    @Column(name = "nrendimiento", nullable = false)
    private BigDecimal rendimiento;

    @Column(name = "nestado", nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "afp", fetch = FetchType.LAZY)
    private List<Empleado> empleados;

    @PrePersist
    public void prePersist()
    {
        if(this.estado == null)
        {
            this.estado = 1;
        }
    }


}