package org.rsosa.gestion_reportes.persistence.crud;
// ... imports

import org.rsosa.gestion_reportes.persistence.entity.TrabajadorMunicipalidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudTrabajadorMunicipalEntity extends CrudRepository<TrabajadorMunicipalidad, Long> {
    Optional<TrabajadorMunicipalidad> findByPersonal_CodigoPersonal(Long codigoPersonal);

    List<TrabajadorMunicipalidad> findByMunicipalidad_CodigoMunicipalidad(Long codigoMunicipalidad);
}