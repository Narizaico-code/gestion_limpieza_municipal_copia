package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.persistence.entity.TipoReporte;
import org.springframework.data.repository.CrudRepository;

public interface CrudTipoReporteEntity extends CrudRepository<TipoReporte, Long> {
    TipoReporte  findFirstByNombre(String nombre);
}
