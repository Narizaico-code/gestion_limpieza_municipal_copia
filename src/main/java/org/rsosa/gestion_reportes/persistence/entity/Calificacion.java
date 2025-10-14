package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_calificacion")
    private Long codigoCalificacion;
    private Integer valoracion;

    @ManyToOne
    @JoinColumn(name = "codigo_vecino", nullable = false)
    private Vecino vecino;

    @ManyToOne
    @JoinColumn(name = "codigo_reporte", nullable = false)
    private Reporte reporte;
}