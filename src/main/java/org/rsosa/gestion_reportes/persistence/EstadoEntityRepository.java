package org.rsosa.gestion_reportes.persistence;


import org.rsosa.gestion_reportes.dominio.dto.EstadoDto;
import org.rsosa.gestion_reportes.dominio.exception.EstadoNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.EstadoYaExisteException;
import org.rsosa.gestion_reportes.dominio.repository.EstadoRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudEstadoEntity;
import org.rsosa.gestion_reportes.persistence.entity.Estado;
import org.rsosa.gestion_reportes.web.mapper.EstadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstadoEntityRepository implements EstadoRepository {

    private final CrudEstadoEntity crudEstadoEntity;
    private final EstadoMapper estadoMapper;

    public EstadoEntityRepository(CrudEstadoEntity crudEstadoEntity, EstadoMapper estadoMapper) {
        this.crudEstadoEntity = crudEstadoEntity;
        this.estadoMapper = estadoMapper;
    }

    @Override
    public List<EstadoDto> obtenerTodo() {
        return this.estadoMapper.toDto(this.crudEstadoEntity.findAll());
    }

    @Override
    public EstadoDto obtenerEstadoPorCodigo(Long id) {
        Estado estado = this.crudEstadoEntity.findById(id).orElse(null);
        if (estado == null){
            throw new EstadoNoExisteException(id);
        }
        return this.estadoMapper.toDto(estado);
    }

    @Override
    public EstadoDto obtenerEstadoPorNombre(String nombre) {
        Optional<Estado> estado = this.crudEstadoEntity.findByNombre(nombre);
        if (estado.isEmpty()){
            throw new EstadoNoExisteException(nombre);
        }
        return this.estadoMapper.toDto(estado.orElse(null));
    }

    @Override
    public EstadoDto guardarEstado(EstadoDto estadoDto) {
        String nombre = String.valueOf(estadoDto.name()).toUpperCase();
        if (!this.crudEstadoEntity.findByNombre(nombre).isEmpty()){
            throw new EstadoYaExisteException(nombre);
        }
        Estado estado = this.estadoMapper.toEntity(estadoDto);
        estado = this.crudEstadoEntity.save(estado);
        return this.estadoMapper.toDto(estado);
    }

    @Override
    public EstadoDto actualizarEstado(Long id, EstadoDto estadoDto) {
        Estado estado = this.crudEstadoEntity.findById(id).orElse(null);
        if (estado == null){
            throw new EstadoNoExisteException(id);
        }else {
            this.estadoMapper.updateEntityFromDto(estadoDto, estado);
            estado = this.crudEstadoEntity.save(estado);
            return this.estadoMapper.toDto(estado);
        }
    }

    @Override
    public void eliminarEstado(Long id) {
        Estado estado = this.crudEstadoEntity.findById(id).orElse(null);
        if (estado == null){
            throw new EstadoNoExisteException(id);
        }else {
            this.crudEstadoEntity.deleteById(id);
        }
    }
}