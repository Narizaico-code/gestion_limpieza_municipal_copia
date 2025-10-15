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

    public MunicipalidadDto obtenerPorCodigo(Long id) {
        return this.municipalidadRepository.obtenerMunicipalidadPorId(id);
    }

    public MunicipalidadDto guardarMunicipalidad(MunicipalidadDto dto) {
        return this.municipalidadRepository.guardarMunicipalidad(dto);
    }

    public MunicipalidadDto actualizarMunicipalidad(Long id, MunicipalidadDto dto) {
        return this.municipalidadRepository.actualizarMunicipalidad(id, dto);
    }

    public void eliminarMunicipalidad(Long id) {
        this.municipalidadRepository.eliminarMunicipalidad(id);
    }
}