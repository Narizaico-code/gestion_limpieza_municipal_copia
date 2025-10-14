package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.repository.ContratacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratacionService {

    private final ContratacionRepository contratacionRepository;

    public ContratacionService(ContratacionRepository contratacionRepository) {
        this.contratacionRepository = contratacionRepository;
    }

    public List<ContratacionDto> obtenerTodo() {
        return this.contratacionRepository.obtenerTodo();
    }

    public ContratacionDto obtenerContratacionPorId(Long id) {
        return this.contratacionRepository.obtenerContratacionPorId(id);
    }

    public ContratacionDto guardarContratacion(ContratacionDto contratacionDto) {
        return this.contratacionRepository.guardarContratacion(contratacionDto);
    }

    public ContratacionDto actualizarContratacion(Long id, ContratacionDto contratacionDto) {
        return this.contratacionRepository.actualizarContratacion(id, contratacionDto);
    }

    public void eliminarContratacion(Long id) {
        this.contratacionRepository.eliminarContratacion(id);
    }
}
