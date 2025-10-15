package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.AdministradorMunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.repository.AdministradorMunicipalidadRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudAdministradorEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudAdministradorMunicipalidadEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudMunicipalidadEntity;
import org.rsosa.gestion_reportes.persistence.entity.Administrador;
import org.rsosa.gestion_reportes.persistence.entity.AdministradorMunicipalidad;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;
import org.rsosa.gestion_reportes.web.mapper.AdministradorMunicipalidadMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdministradorMunicipalidadEntityRepository implements AdministradorMunicipalidadRepository {
    private final CrudAdministradorMunicipalidadEntity crudAdminMunicipalidad;
    private final CrudAdministradorEntity crudAdministrador;
    private final CrudMunicipalidadEntity crudMunicipalidad;
    private final AdministradorMunicipalidadMapper mapper;

    public AdministradorMunicipalidadEntityRepository(
            CrudAdministradorMunicipalidadEntity crudAdminMunicipalidad,
            CrudAdministradorEntity crudAdministrador,
            CrudMunicipalidadEntity crudMunicipalidad,
            AdministradorMunicipalidadMapper mapper
    ) {
        this.crudAdminMunicipalidad = crudAdminMunicipalidad;
        this.crudAdministrador = crudAdministrador;
        this.crudMunicipalidad = crudMunicipalidad;
        this.mapper = mapper;
    }

    @Override
    public List<AdministradorMunicipalidadDto> obtenerTodo() {
        return this.mapper.toDto(this.crudAdminMunicipalidad.findAll());
    }

    @Override
    public Optional<AdministradorMunicipalidadDto> obtenerPorMunicipalidad(Long codigoMunicipalidad) {
        return this.crudAdminMunicipalidad
                .findByMunicipalidad_CodigoMunicipalidad(codigoMunicipalidad)
                .map(this.mapper::toDto);
    }

    @Override
    public List<AdministradorMunicipalidadDto> obtenerPorAdministrador(Long codigoAdmin) {
        return this.mapper.toDto(this.crudAdminMunicipalidad.findByAdministrador_CodigoAdmin(codigoAdmin));
    }

    @Override
    public AdministradorMunicipalidadDto asignarAdminAMunicipalidad(Long codigoAdmin, Long codigoMunicipalidad) {
        Administrador admin = this.crudAdministrador.findById(codigoAdmin)
                .orElseThrow(() -> new CatalogoInvalidoException("Administrador con código " + codigoAdmin + " no existe."));

        Municipalidad municipalidad = this.crudMunicipalidad.findById(codigoMunicipalidad)
                .orElseThrow(() -> new CatalogoInvalidoException("Municipalidad con código " + codigoMunicipalidad + " no existe."));

        Optional<AdministradorMunicipalidad> existente = this.crudAdminMunicipalidad
                .findByAdministrador_CodigoAdminAndMunicipalidad_CodigoMunicipalidad(codigoAdmin, codigoMunicipalidad);

        if (existente.isPresent()) {
            throw new CatalogoInvalidoException("El administrador ya está asignado a esta municipalidad.");
        }

        AdministradorMunicipalidad nueva = new AdministradorMunicipalidad();
        nueva.setAdministrador(admin);
        nueva.setMunicipalidad(municipalidad);

        nueva = this.crudAdminMunicipalidad.save(nueva);
        return this.mapper.toDto(nueva);
    }

    @Override
    public void desasignarAdminDeMunicipalidad(Long codigoAdminMunicipalidad) {
        AdministradorMunicipalidad registro = this.crudAdminMunicipalidad.findById(codigoAdminMunicipalidad)
                .orElseThrow(() -> new CatalogoInvalidoException("Registro de admin-municipalidad no existe."));
        this.crudAdminMunicipalidad.deleteById(codigoAdminMunicipalidad);
    }
}