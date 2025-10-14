package org.rsosa.gestion_reportes.persistence;


import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.exception.VecinoNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.VecinoYaExisteException;
import org.rsosa.gestion_reportes.dominio.repository.VecinoRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudVecinoEntity;
import org.rsosa.gestion_reportes.persistence.entity.Vecino;
import org.rsosa.gestion_reportes.web.mapper.VecinoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VecinoEntityRepository implements VecinoRepository {
    private final CrudVecinoEntity crudVecinoEntity;
    private final VecinoMapper vecinoMapper;

    public VecinoEntityRepository(CrudVecinoEntity crudVecinoEntity, VecinoMapper vecinoMapper) {
        this.crudVecinoEntity = crudVecinoEntity;
        this.vecinoMapper = vecinoMapper;
    }

    @Override
    public List<VecinoDto> obtenerTodo() {
        return this.vecinoMapper.toDto(this.crudVecinoEntity.findAll());
    }

    @Override
    public VecinoDto obtenerVecinoPorCodigo(Long id) {
        Vecino vecino = this.crudVecinoEntity.findById(id).orElse(null);
        if (vecino == null){
            throw new VecinoNoExisteException(id);
        }
        return this.vecinoMapper.toDto(vecino);
    }

    @Override
    public VecinoDto guardarVecino(VecinoDto vecinoDto) {
        if (this.crudVecinoEntity.findFirstByCorreoElectronico(vecinoDto.email()) != null){
            throw new VecinoYaExisteException(vecinoDto.email());
        }
        Vecino vecino = this.vecinoMapper.toEntity(vecinoDto);
        vecino = this.crudVecinoEntity.save(vecino);
        return this.vecinoMapper.toDto(vecino);
    }

    @Override
    public VecinoDto actualizarVecino(Long id, VecinoDto vecinoDto) {
        Vecino vecino = this.crudVecinoEntity.findById(id).orElse(null);
        if (vecino == null){
            throw new VecinoNoExisteException(id);
        }else {
            this.vecinoMapper.updateEntityFromDto(vecinoDto, vecino);
            vecino = this.crudVecinoEntity.save(vecino);
            return this.vecinoMapper.toDto(vecino);
        }
    }

    @Override
    public void eliminarVecino(Long id) {
        Vecino vecino = this.crudVecinoEntity.findById(id).orElse(null);
        if (vecino == null) {
            throw new VecinoNoExisteException(id);
        }else {
            this.crudVecinoEntity.deleteById(id);
        }
    }
}