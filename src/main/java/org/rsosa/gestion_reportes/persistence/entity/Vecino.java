package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vecinos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vecino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_vecino")
    private Long codigoVecino;
    @Column(nullable = false)
    private String nombre;
    private String contrasena;
    private String telefono;
    @Column(name = "correo_electronico",nullable = false, unique = true)
    private String correoElectronico;
}