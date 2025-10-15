package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.repository.TrabajadorMunicipalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorMunicipalidadService {
    private final TrabajadorMunicipalidadRepository trabajadorMunicipalidadRepository;

    public TrabajadorMunicipalidadService(TrabajadorMunicipalidadRepository trabajadorMunicipalidadRepository) {
        this.trabajadorMunicipalidadRepository = trabajadorMunicipalidadRepository;
    }

    public List<TrabajadorMunicipalDto> obtenerTodo() {
        return this.trabajadorMunicipalidadRepository.obtenerTodo();
    }

    public TrabajadorMunicipalDto obtenerPorCodigo(Long id) {
        return this.trabajadorMunicipalidadRepository.obtenerPorCodigo(id);
    }

    public TrabajadorMunicipalDto guardarTrabajadorMunicipalidad(TrabajadorMunicipalDto trabajadorMunicipalDto) {
        return this.trabajadorMunicipalidadRepository.guardarTrabajadorMunicipalidad(trabajadorMunicipalDto);
    }

    public TrabajadorMunicipalDto actualizarTrabajadorMunicipalidad(Long id, TrabajadorMunicipalDto trabajadorMunicipalDto) {
        return this.trabajadorMunicipalidadRepository.actualizarTrabajadorMunicipalidad(id, trabajadorMunicipalDto);
    }

    public void eliminarTrabajadorMunicipalidad(Long id) {
        this.trabajadorMunicipalidadRepository.eliminarTrabajadorMunicipalidad(id);
    }

    public List<TrabajadorMunicipalDto> obtenerPorPersonal(Long codigoPersonal) {
        return this.trabajadorMunicipalidadRepository.obtenerPorPersonal(codigoPersonal);
    }

    public List<TrabajadorMunicipalDto> obtenerPorMunicipalidad(Long codigoMunicipalidad) {
        return this.trabajadorMunicipalidadRepository.obtenerPorMunicipalidad(codigoMunicipalidad);
    }

    public List<TrabajadorMunicipalDto> obtenerPorZona(String zona) {
        return this.trabajadorMunicipalidadRepository.obtenerPorZona(zona);
    }
}