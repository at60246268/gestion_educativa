package pe.edu.idat.gestion.app.ms.planillas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tm_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "nidusuario")
    private Long idUsuario;

    @Column(name = "susername", nullable = false, unique = true,  length = 20)
    private String username;

    @Column(name = "spassword", nullable = false,  length = 255)
    private String password;

    @Column(name = "semail", nullable = false,  length = 60)
    private String email;

    @Column(name = "nestado", nullable = false)
    private Integer estado;

    @PrePersist
    public void prePersist()
    {
        if(this.estado == null)
        {


        }
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tt_usuario_rol",
            joinColumns = @JoinColumn(name = "nidusuario"),
            inverseJoinColumns = @JoinColumn(name = "nidrol")
    )

    @Builder.Default
    private Set<Rol> roles = new HashSet<>();


}
