package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.service.MunicipalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("municipalidadControllerWeb")
@Scope("view")
@Data
public class MunicipalidadControllerWeb implements Serializable {

    @Autowired
    private MunicipalidadService municipalidadService;

    private List<MunicipalidadDto> municipalidades;
    private MunicipalidadDto municipalidadSeleccionada;

    @PostConstruct
    public void init() {
        cargarMunicipalidades();
    }

    public void cargarMunicipalidades() {
        this.municipalidades = municipalidadService.obtenerTodo();
    }

    public void agregarMunicipalidad() {
        this.municipalidadSeleccionada = new MunicipalidadDto(null, null, null);
    }

    public void guardarMunicipalidad() {
        try {
            if (municipalidadSeleccionada.municipalityId() == null) {
                municipalidadService.guardarMunicipalidad(municipalidadSeleccionada);
                addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Municipalidad creada");
            } else {
                municipalidadService.actualizarMunicipalidad(
                        municipalidadSeleccionada.municipalityId(),
                        municipalidadSeleccionada
                );
                addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Municipalidad actualizada");
            }
            cargarMunicipalidades();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public void eliminarMunicipalidad() {
        try {
            municipalidadService.eliminarMunicipalidad(municipalidadSeleccionada.municipalityId());
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Municipalidad eliminada");
            cargarMunicipalidades();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}