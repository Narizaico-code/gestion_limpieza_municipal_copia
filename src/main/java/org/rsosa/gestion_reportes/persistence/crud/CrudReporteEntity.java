package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Reporte;
import org.springframework.data.repository.CrudRepository;

public interface CrudReporteEntity extends CrudRepository<Reporte, Long> {
    Iterable<Reporte> findAllByVecino_CodigoVecino(Long codigoVecino);
    Iterable<Reporte> findAllByEstado_CodigoEstado(Long codigoEstado);
    Iterable<Reporte> findAllByPersonalAsignado_CodigoPersonal(Long codigoPersonal);
    Iterable<Reporte> findAllByTipoReporte_CodigoTipoReporte(Integer codigoTipoReporte);

    Iterable<Reporte> findAllByEstado_Nombre(String nombreEstado);
    Iterable<Reporte> findAllByPersonalAsignado_Nombre(String nombrePersonal);
    Iterable<Reporte> findAllByTipoReporte_Nombre(String nombreTipoReporte);
    Iterable<Reporte> findAllByVecino_Nombre(String nombre);
}