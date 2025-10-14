package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Contratacion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudContratacionEntity extends CrudRepository<Contratacion, Long> {
    Optional<Contratacion> findFirstByVacante(String nombre);
    Iterable<Contratacion> findByMunicipalidad_ZonaAndVacantesDisponiblesGreaterThan(String zona, int minVacantes);
}