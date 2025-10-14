package org.rsosa.gestion_reportes.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reportes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_reporte")
    private Long codigoReporte;
    private String descripcion;
    private String zona;

    @ManyToOne
    @JoinColumn(name = "codigo_estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "codigo_vecino")
    private Vecino vecino;

    @ManyToOne
    @JoinColumn(name = "codigo_personal")
    private Personal personalAsignado;

    @ManyToOne
    @JoinColumn(name = "codigo_admin")
    private Administrador administradorAsignado;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_reporte", nullable = false)
    private TipoReporte tipoReporte;
}
