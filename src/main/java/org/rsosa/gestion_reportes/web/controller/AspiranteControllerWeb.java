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
import java.util.Collections;
import java.util.List;

@Component("aspiranteControllerWeb")
@Scope("view")
@Data
public class AspiranteControllerWeb implements Serializable {

    @Autowired
    private AspiranteService aspiranteService;

    @Autowired
    private ContratacionService contratacionService;

    private List<AspiranteDto> aspirantes;
    private Long contratacionId;
    private ContratacionDto contratacionSeleccionada;

    // Para formulario público
    private List<ContratacionDto> contratacionesDisponibles;
    private Long contratacionIdPublico;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @PostConstruct
    public void init() {
        cargarContratacionesDisponibles();
    }

    public void cargarAspirantesPorContratacion() {
        if (contratacionId != null) {
            this.aspirantes = Collections.singletonList(AspiranteService.obtenerPorCodigo(contratacionId));
            this.contratacionSeleccionada = contratacionService.obtenerPorCodigo(contratacionId);
        }
    }

    private void cargarContratacionesDisponibles() {
        this.contratacionesDisponibles = contratacionService.obtenerTodo().stream()
                .filter(c -> c.available_vaca() != null && c.available_vaca() > 0)
                .toList();
    }

    public void postular() {
        try {
            AspiranteDto dto = new AspiranteDto(
                    null,
                    nombre,
                    apellido,
                    telefono,
                    email,
                    new ContratacionDto(contratacionIdPublico, null, null, null, null)
            );

            aspiranteService.guardarAspirante(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Postulación enviada exitosamente");
            limpiarFormulario();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public void contratarAspirante(Long aspiranteId) {
        try {
            aspiranteService.guardarAspirante(AspiranteService.obtenerPorCodigo(aspiranteId));
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Aspirante contratado exitosamente");
            cargarAspirantesPorContratacion();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

//    public void contratarAspirante(Long aspiranteId) {
//        try {
//            aspiranteService.contratarAspirante(aspiranteId);
//            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Aspirante contratado exitosamente");
//            cargarAspirantesPorContratacion();
//        } catch (Exception e) {
//            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
//        }
//    }

    private void limpiarFormulario() {
        this.nombre = null;
        this.apellido = null;
        this.telefono = null;
        this.email = null;
        this.contratacionIdPublico = null;
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}