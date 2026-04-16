package pe.edu.idat.gestion.app.ms.planillas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tm_banco")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Banco
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_banco")
    @SequenceGenerator(name = "seq_tm_banco", sequenceName = "seq_tm_banco", allocationSize = 1)
    @Column(name = "nidbanco")
    private Long idBanco;

    @Column(name = "snombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "snumerocuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name = "nestado")
    private Integer estado;
}
