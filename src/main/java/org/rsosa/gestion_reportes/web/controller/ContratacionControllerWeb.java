package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.service.ContratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("contratacionControllerWeb")
@Scope("view")
@Data
public class ContratacionControllerWeb implements Serializable {

    @Autowired
    private ContratacionService contratacionService;

    private List<ContratacionDto> contrataciones;
    private ContratacionDto nuevaContratacion;

    // Campos para crear
    private String puesto;
    private Double salario;
    private Integer vacantesDisponibles;
    private Long municipalidadId;

    @PostConstruct
    public void init() {
        cargarContrataciones();
        inicializarNuevaContratacion();
    }

    private void inicializarNuevaContratacion() {
        this.puesto = "";
        this.salario = 0.0;
        this.vacantesDisponibles = 1;
    }

    public void cargarContrataciones() {
        this.contrataciones = contratacionService.obtenerTodo();
    }

    public void crearContratacion() {
        try {
            ContratacionDto dto = new ContratacionDto(
                    null,
                    salario,
                    puesto,
                    vacantesDisponibles,
                    new MunicipalidadDto(municipalidadId, null, null)
            );

            contratacionService.guardarContratacion(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Contratación creada exitosamente");
            cargarContrataciones();
            inicializarNuevaContratacion();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public String verAspirantes(Long contratacionId) {
        return "/admin/aspirantes?faces-redirect=true&contratacionId=" + contratacionId;
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}