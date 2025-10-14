package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;
import org.rsosa.gestion_reportes.dominio.repository.CalificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {
    private final CalificacionRepository calificacionRepository;

    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    public List<CalificacionDto> obtenerTodo(){
        return this.calificacionRepository.obtenerTodo();
    }

    public CalificacionDto guardarCalificacion(CalificacionDto calificacionDto){
        return this.calificacionRepository.guardarCalificacion(calificacionDto);
    }
}