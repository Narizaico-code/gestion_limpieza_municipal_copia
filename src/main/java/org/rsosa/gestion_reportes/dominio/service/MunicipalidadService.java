package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.repository.MunicipalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalidadService {

    private final MunicipalidadRepository municipalidadRepository;

    public MunicipalidadService(MunicipalidadRepository municipalidadRepository) {
        this.municipalidadRepository = municipalidadRepository;
    }

    public List<MunicipalidadDto> obtenerTodo() {
        return this.municipalidadRepository.obtenerTodo();
    }

    public MunicipalidadDto obtenerMunicipalidadPorId(Long id) {
        return this.municipalidadRepository.obtenerMunicipalidadPorId(id);
    }

    public MunicipalidadDto guardarMunicipalidad(MunicipalidadDto municipalidadDto) {
        return this.municipalidadRepository.guardarMunicipalidad(municipalidadDto);
    }

    public MunicipalidadDto actualizarMunicipalidad(Long id, MunicipalidadDto municipalidadDto) {
        return this.municipalidadRepository.actualizarMunicipalidad(id, municipalidadDto);
    }

    public void eliminarMunicipalidad(Long id) {
        this.municipalidadRepository.eliminarMunicipalidad(id);
    }
}
