package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Municipalidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Municipalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_municipalidad")
    private Long codigoMunicipalidad;
    @Column(nullable = false)
    private String zona;
    @Column(nullable = false)
    private String ubicacion;
}
