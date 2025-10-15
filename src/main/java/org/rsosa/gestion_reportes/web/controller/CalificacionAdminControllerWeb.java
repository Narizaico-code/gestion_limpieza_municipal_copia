package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;
import org.rsosa.gestion_reportes.dominio.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("calificacionAdminControllerWeb")
@Scope("view")
@Data
public class CalificacionAdminControllerWeb implements Serializable {

    @Autowired
    private CalificacionService calificacionService;

    private List<CalificacionDto> calificaciones;

    @PostConstruct
    public void init() {
        cargarCalificaciones();
    }

    public void cargarCalificaciones() {
        this.calificaciones = calificacionService.obtenerTodo();
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}