package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;

import org.rsosa.gestion_reportes.dominio.repository.AdministradorRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudAdministradorEntity;
import org.rsosa.gestion_reportes.persistence.entity.Administrador;
import org.rsosa.gestion_reportes.web.mapper.AdministradorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministradorEntityRepository implements AdministradorRepository {

    private final CrudAdministradorEntity crudAdministradorEntity;
    private final AdministradorMapper administradorMapper;

    public AdministradorEntityRepository(CrudAdministradorEntity crudAdministradorEntity, AdministradorMapper administradorMapper) {
        this.crudAdministradorEntity = crudAdministradorEntity;
        this.administradorMapper = administradorMapper;
    }

    @Override
    public List<AdministradorDto> obtenerTodo() {
        return this.administradorMapper.toDto(this.crudAdministradorEntity.findAll());
    }

    @Override
    public AdministradorDto obtenerAdministradorPorId(Long id) {
        Administrador administrador = this.crudAdministradorEntity.findById(id).orElse(null);
        if (administrador == null) {
            return null;
        }
        return this.administradorMapper.toDto(administrador);
    }

    @Override
    public AdministradorDto guardarAdministrador(AdministradorDto administradorDto) {
        Administrador administrador = this.administradorMapper.toEntity(administradorDto);
        administrador = this.crudAdministradorEntity.save(administrador);
        return this.administradorMapper.toDto(administrador);
    }

    @Override
    public AdministradorDto actualizarAdministrador(Long id, AdministradorDto administradorDto) {
        Administrador administrador = this.crudAdministradorEntity.findById(id).orElse(null);
        if (administrador == null) {
            return null;
        } else {
            this.administradorMapper.updateEntityFromDto(administradorDto, administrador);
            administrador = this.crudAdministradorEntity.save(administrador);
            return this.administradorMapper.toDto(administrador);
        }
    }

    @Override
    public void eliminarAdministrador(Long id) {
        this.crudAdministradorEntity.deleteById(id);
    }
}