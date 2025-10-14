package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Aspirantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aspirante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_aspirante")
    private Long codigoAspirante;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String correo;
    @ManyToOne
    @JoinColumn(name = "codigo_contratacion", nullable = false)
    private Contratacion contratacion;
}
