package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.service.AspiranteService;
import org.rsosa.gestion_reportes.dominio.service.ContratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("aspiranteAdminControllerWeb")
@Scope("view")
@Data
public class AspiranteAdminControllerWeb implements Serializable {

    @Autowired
    private AspiranteService aspiranteService;

    @Autowired
    private ContratacionService contratacionService;

    private List<AspiranteDto> aspirantes;
    private List<ContratacionDto> contrataciones;
    private Long contratacionSeleccionada;

    @PostConstruct
    public void init() {
        cargarContrataciones();
    }

    private void cargarContrataciones() {
        this.contrataciones = contratacionService.obtenerTodo();
    }

    public void cargarAspirantesPorContratacion() {
        if (contratacionSeleccionada != null) {
            this.aspirantes = aspiranteService.obtenerPorContratacion(contratacionSeleccionada);
        }
    }

    public void contratarAspirante(Long aspiranteId) {
        try {
            aspiranteService.contratarAspirante(aspiranteId);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Aspirante contratado exitosamente");
            cargarAspirantesPorContratacion();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}