package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudMunicipalidadEntity extends CrudRepository<Municipalidad, Long> {
    Optional<Municipalidad> findByZona(String zona);
}