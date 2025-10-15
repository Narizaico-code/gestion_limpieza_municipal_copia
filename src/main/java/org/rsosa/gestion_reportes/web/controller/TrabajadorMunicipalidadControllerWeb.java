package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.service.PersonalService;
import org.rsosa.gestion_reportes.dominio.service.TrabajadorMunicipalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("trabajadorMunicipalidadControllerWeb")
@Scope("view")
@Data
public class TrabajadorMunicipalidadControllerWeb implements Serializable {

    @Autowired
    private TrabajadorMunicipalidadService trabajadorService;

    @Autowired
    private PersonalService personalService;

    private List<TrabajadorMunicipalDto> trabajadores;
    private List<PersonalDto> personalDisponible;

    private Long personalSeleccionado;
    private Long municipalidadId;
    private String zonaFiltro;

    @PostConstruct
    public void init() {
        cargarTrabajadores();
        cargarPersonalDisponible();
    }

    public void cargarTrabajadores() {
        this.trabajadores = trabajadorService.obtenerTodo();
    }

    public void cargarPorZona() {
        if (zonaFiltro != null && !zonaFiltro.isEmpty()) {
            this.trabajadores = trabajadorService.obtenerPorZona(zonaFiltro);
        } else {
            cargarTrabajadores();
        }
    }

    private void cargarPersonalDisponible() {
        this.personalDisponible = personalService.obtenerTodo();
    }

    public void asignarPersonalAZona() {
        try {
            TrabajadorMunicipalDto dto = new TrabajadorMunicipalDto(
                    null,
                    new PersonalDto(personalSeleccionado, null, null, null, null, null),
                    new MunicipalidadDto(municipalidadId, null, null)
            );

            trabajadorService.guardarTrabajadorMunicipalidad(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Personal asignado a la zona");
            cargarTrabajadores();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}