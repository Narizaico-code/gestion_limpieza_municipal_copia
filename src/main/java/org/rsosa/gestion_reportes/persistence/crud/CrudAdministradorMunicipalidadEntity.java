package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.AdministradorMunicipalidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudAdministradorMunicipalidadEntity extends CrudRepository<AdministradorMunicipalidad, Long> {
    Optional<AdministradorMunicipalidad> findByMunicipalidad_CodigoMunicipalidad(Long codigoMunicipalidad);
    List<AdministradorMunicipalidad> findByAdministrador_CodigoAdmin(Long codigoAdmin);
    Optional<AdministradorMunicipalidad> findByAdministrador_CodigoAdminAndMunicipalidad_CodigoMunicipalidad(
            Long codigoAdmin,
            Long codigoMunicipalidad
    );
}