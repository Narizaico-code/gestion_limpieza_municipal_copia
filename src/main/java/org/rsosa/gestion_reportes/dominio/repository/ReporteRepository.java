package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.ModReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;

import java.util.List;

public interface ReporteRepository {
    List<ReporteDto> obtenerTodo();
    List<ReporteDto> obtenerReportesPorVecino(String vecino);
    List<ReporteDto> obtenerReportesPorEstado(String estado);
    List<ReporteDto> obtenerReportesPorTipoReporte(String tipoReporte);
    List<ReporteDto> obtenerReportesPorPersonal(String persona);
    ReporteDto obtenerReportePorCodigo(Long id);
    ReporteDto guardarReporte(ReporteDto reporteDto);
    ReporteDto actualizarReporte(Long id, ModReporteDto modReporteDto);
    void eliminarReporte(Long id);
}