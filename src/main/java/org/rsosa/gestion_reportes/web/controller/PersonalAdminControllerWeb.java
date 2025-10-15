package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.service.MunicipalidadService;
import org.rsosa.gestion_reportes.dominio.service.PersonalService;
import org.rsosa.gestion_reportes.dominio.service.TrabajadorMunicipalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("personalAdminControllerWeb")
@Scope("view")
@Data
public class PersonalAdminControllerWeb implements Serializable {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private TrabajadorMunicipalidadService trabajadorService;

    @Autowired
    private MunicipalidadService municipalidadService;

    private List<TrabajadorMunicipalDto> trabajadores;
    private List<MunicipalidadDto> municipalidades;
    private String filtroEstado;

    // Para asignar
    private Long personalSeleccionado;
    private Long municipalidadSeleccionada;
    private List<PersonalDto> personalDisponible;

    @PostConstruct
    public void init() {
        cargarTrabajadores();
        cargarMunicipalidades();
        cargarPersonalDisponible();
    }

    public void cargarTrabajadores() {
        this.trabajadores = trabajadorService.obtenerTodo();
    }

    private void cargarMunicipalidades() {
        this.municipalidades = municipalidadService.obtenerTodo();
    }

    private void cargarPersonalDisponible() {
        this.personalDisponible = personalService.obtenerTodo();
    }

    public void aplicarFiltros() {
        if (filtroEstado != null && !filtroEstado.isEmpty()) {
            this.trabajadores = trabajadorService.obtenerTodo().stream()
                    .filter(tm -> tm.staff().state().name().equalsIgnoreCase(filtroEstado))
                    .toList();
        } else {
            cargarTrabajadores();
        }
    }

    public void asignarPersonalAZona() {
        try {
            TrabajadorMunicipalDto dto = new TrabajadorMunicipalDto(
                    null,
                    new PersonalDto(personalSeleccionado, null, null, null, null, null),
                    new MunicipalidadDto(municipalidadSeleccionada, null, null)
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
