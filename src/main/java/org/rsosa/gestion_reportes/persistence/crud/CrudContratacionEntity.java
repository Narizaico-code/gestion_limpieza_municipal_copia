package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Contratacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudContratacionEntity extends CrudRepository<Contratacion, Long> {
    List<Contratacion> findByMunicipalidad_CodigoMunicipalidad(Long codigoMunicipalidad);
    List<Contratacion> findByMunicipalidad_Zona(String zona);
}