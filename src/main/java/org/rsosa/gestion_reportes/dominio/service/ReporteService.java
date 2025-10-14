package org.rsosa.gestion_reportes.dominio.service;


import org.rsosa.gestion_reportes.dominio.dto.ModReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.dominio.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {
    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<ReporteDto> obtenerTodo(){
        return this.reporteRepository.obtenerTodo();
    }

    public List<ReporteDto> obtenerReportesPorVecino(String vecino){
        return this.reporteRepository.obtenerReportesPorVecino(vecino);
    }

    public List<ReporteDto> obtenerReportesPorEstado(String estado){
        return this.reporteRepository.obtenerReportesPorEstado(estado);
    }

    public List<ReporteDto> obtenerReportesPorTipoReporte(String tipoReporte){
        return this.reporteRepository.obtenerReportesPorTipoReporte(tipoReporte);
    }

    public List<ReporteDto> obtenerReportesPorPersonal(String persona){
        return this.reporteRepository.obtenerReportesPorPersonal(persona);
    }

    public ReporteDto obtenerReportePorCodigo(Long id){
        return this.reporteRepository.obtenerReportePorCodigo(id);
    }

    public ReporteDto guardarReporte(ReporteDto reporteDto){
        return this.reporteRepository.guardarReporte(reporteDto);
    }

    public ReporteDto actualizarReporte(Long id, ModReporteDto modReporteDto){
        return this.reporteRepository.actualizarReporte(id, modReporteDto);
    }

    public void eliminarReporte(Long id){
        this.reporteRepository.eliminarReporte(id);
    }
}