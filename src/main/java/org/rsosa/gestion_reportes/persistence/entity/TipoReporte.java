package org.rsosa.gestion_reportes.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TipoReportes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoReporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_tipo_reporte")
    private  Long codigoTipoReporte;
    @Column(nullable = false)
    private String nombre;
}
