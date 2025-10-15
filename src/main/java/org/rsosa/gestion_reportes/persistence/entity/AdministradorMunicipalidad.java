package org.rsosa.gestion_reportes.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador_municipalidad", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"admin_id", "codigo_municipalidad"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorMunicipalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoAdminMunicipalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = false)
    private Administrador administrador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_municipalidad", nullable = false)
    private Municipalidad municipalidad;
}