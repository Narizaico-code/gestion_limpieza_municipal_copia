package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.service.PersonalService;
import org.rsosa.gestion_reportes.dominio.service.TrabajadorMunicipalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("personalControllerWeb")
@Scope("view")
@Data
public class PersonalControllerWeb implements Serializable {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private TrabajadorMunicipalidadService trabajadorService;

    private List<TrabajadorMunicipalDto> personalZona;
    private TrabajadorMunicipalDto personalSeleccionado;
    private String filtroEstado;
    private String filtroBusqueda;

    @PostConstruct
    public void init() {
        cargarPersonalZona();
    }

    public void cargarPersonalZona() {
        // Obtener zona del admin logueado (si es necesario)
        // Por ahora cargamos todo el personal
        this.personalZona = trabajadorService.obtenerTodo();
    }

    public void aplicarFiltros() {
        if (filtroEstado != null && !filtroEstado.isEmpty()) {
            this.personalZona = trabajadorService.obtenerTodo().stream()
                    .filter(tm -> tm.staff().state().name().equalsIgnoreCase(filtroEstado))
                    .toList();
        } else {
            cargarPersonalZona();
        }
    }

    public void limpiarFiltros() {
        this.filtroEstado = null;
        this.filtroBusqueda = null;
        cargarPersonalZona();
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
