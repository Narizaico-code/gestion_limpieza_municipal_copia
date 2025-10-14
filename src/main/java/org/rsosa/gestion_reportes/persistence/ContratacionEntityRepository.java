package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.repository.ContratacionRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudContratacionEntity;
import org.rsosa.gestion_reportes.persistence.entity.Contratacion;
import org.rsosa.gestion_reportes.web.mapper.ContratacionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratacionEntityRepository implements ContratacionRepository {

    private final CrudContratacionEntity crudContratacionEntity;
    private final ContratacionMapper contratacionMapper;

    public ContratacionEntityRepository(CrudContratacionEntity crudContratacionEntity, ContratacionMapper contratacionMapper) {
        this.crudContratacionEntity = crudContratacionEntity;
        this.contratacionMapper = contratacionMapper;
    }

    @Override
    public List<ContratacionDto> obtenerTodo() {
        return this.contratacionMapper.toDto(this.crudContratacionEntity.findAll());
    }

    @Override
    public ContratacionDto obtenerContratacionPorId(Long id) {
        Contratacion contratacion = this.crudContratacionEntity.findById(id).orElse(null);
        if (contratacion == null) {
            return null;
        }
        return this.contratacionMapper.toDto(contratacion);
    }

    @Override
    public ContratacionDto guardarContratacion(ContratacionDto contratacionDto) {
        Contratacion contratacion = this.contratacionMapper.toEntity(contratacionDto);
        contratacion = this.crudContratacionEntity.save(contratacion);
        return this.contratacionMapper.toDto(contratacion);
    }

    @Override
    public ContratacionDto actualizarContratacion(Long id, ContratacionDto contratacionDto) {
        Contratacion contratacion = this.crudContratacionEntity.findById(id).orElse(null);
        if (contratacion == null) {
            return null;
        } else {
            this.contratacionMapper.updateEntityFromDto(contratacionDto, contratacion);
            contratacion = this.crudContratacionEntity.save(contratacion);
            return this.contratacionMapper.toDto(contratacion);
        }
    }

    @Override
    public void eliminarContratacion(Long id) {
        this.crudContratacionEntity.deleteById(id);
    }
}
