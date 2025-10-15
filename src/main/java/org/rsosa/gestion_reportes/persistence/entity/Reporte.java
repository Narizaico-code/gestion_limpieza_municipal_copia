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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_vecino")
    private Vecino vecino;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_personal")
    private Personal personalAsignado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_admin")
    private Administrador administradorAsignado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_tipo_reporte", nullable = false)
    private TipoReporte tipoReporte;
}
