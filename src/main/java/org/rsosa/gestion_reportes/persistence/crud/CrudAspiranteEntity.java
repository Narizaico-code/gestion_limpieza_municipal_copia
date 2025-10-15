package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Aspirante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudAspiranteEntity extends CrudRepository<Aspirante, Long> {
    List<Aspirante> findByContratacion_CodigoContratacion(Long codigoContratacion);
}