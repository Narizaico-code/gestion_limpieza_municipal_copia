package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TrabajadoresMunicipalidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorMunicipalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoTrabajadorMunicipal;

    @ManyToOne
    @JoinColumn(name = "codigo_personal", nullable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "codigo_municipalidad", nullable = false)
    private Municipalidad municipalidad;

}