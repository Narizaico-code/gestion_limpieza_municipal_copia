package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;

import java.util.List;

public interface TipoReporteRepository {
    List<TipoReporteDto> obtenerTodo();
    TipoReporteDto obtenerTipoReportePorCodigo(Long id);
    TipoReporteDto guardarTipoReporte (TipoReporteDto tipoReporteDto);
    TipoReporteDto actualizarTipoReporte(Long id, TipoReporteDto tipoReporteDto);
    void eliminarTipoReporte(Long id);
}
