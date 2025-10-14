package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.repository.VecinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VecinoService {

    private final VecinoRepository vecinoRepository;

    public VecinoService(VecinoRepository vecinoRepository) {
        this.vecinoRepository = vecinoRepository;
    }

    public List<VecinoDto> obtenerTodo(){
        return this.vecinoRepository.obtenerTodo();
    }

    public VecinoDto obtenerVecinoPorCodigo(Long id){
        return this.vecinoRepository.obtenerVecinoPorCodigo(id);
    }

    public VecinoDto guardarVecino(VecinoDto vecinoDto){
        return this.vecinoRepository.guardarVecino(vecinoDto);
    }

    public VecinoDto actualizarVecino(Long id, VecinoDto vecinoDto){
        return this.vecinoRepository.actualizarVecino(id, vecinoDto);
    }

    public void eliminarVecino(Long id){
        this.vecinoRepository.eliminarVecino(id);
    }
}