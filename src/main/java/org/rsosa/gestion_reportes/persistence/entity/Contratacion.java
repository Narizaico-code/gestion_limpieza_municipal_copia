package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contrataciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contratacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_contratación")
    private Long codigoContratacion;
    @Column(nullable = false)
    private String salario;
    @Column(nullable = false)
    private String vacante;
    private Integer vacantesDisponibles;

    @ManyToOne
    @JoinColumn(name = "codigo_municipalidad", nullable = false)
    private Municipalidad municipalidad;
}
