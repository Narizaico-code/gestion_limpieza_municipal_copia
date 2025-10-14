package org.rsosa.gestion_reportes.dominio.service;


import org.rsosa.gestion_reportes.dominio.dto.EstadoDto;
import org.rsosa.gestion_reportes.dominio.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<EstadoDto> obtenerTodo(){
        return this.estadoRepository.obtenerTodo();
    }

    public EstadoDto obtenerEstadoPorCodigo(Long id){
        return this.estadoRepository.obtenerEstadoPorCodigo(id);
    }

    public EstadoDto obtenerEstadoPorNombre(String nombre){
        return this.estadoRepository.obtenerEstadoPorNombre(nombre);
    }

    public EstadoDto guardarEstado(EstadoDto estadoDto){
        return this.estadoRepository.guardarEstado(estadoDto);
    }

    public EstadoDto actualizarEstado(Long id, EstadoDto estadoDto){
        return this.estadoRepository.actualizarEstado(id, estadoDto);
    }

    public void eliminarEstado(Long id){
        this.estadoRepository.eliminarEstado(id);
    }
}