package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.repository.TipoReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoReporteService {
    private  final TipoReporteRepository tipoReporteRepository;

    public TipoReporteService(TipoReporteRepository tipoReporteRepository){
        this.tipoReporteRepository = tipoReporteRepository;
    }
    public List<TipoReporteDto> obtenerTodo(){
        return  this.tipoReporteRepository.obtenerTodo();
    }
    public TipoReporteDto obtenerTipoReportePorCodigo(Long id){
        return this.tipoReporteRepository.obtenerTipoReportePorCodigo(id);
    }
    public TipoReporteDto guardarTipoReporte(TipoReporteDto tipoReporteDto){
        return this.tipoReporteRepository.guardarTipoReporte(tipoReporteDto);
    }
    public TipoReporteDto actualizarTipoReporte(Long id , TipoReporteDto tipoReporteDto){
        return  this.tipoReporteRepository.actualizarTipoReporte(id,tipoReporteDto);
    }
    public void eliminarTipoReporte(Long id){
        this.tipoReporteRepository.eliminarTipoReporte(id);
    }
}







