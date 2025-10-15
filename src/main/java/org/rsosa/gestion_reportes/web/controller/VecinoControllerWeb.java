package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.service.VecinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component("vecinoControllerWeb")
@Scope("session")
@Data
public class VecinoControllerWeb implements Serializable {

    @Autowired
    private VecinoService vecinoService;

    // Login
    private String email;
    private String password;

    // Registro
    private VecinoDto nuevoVecino = new VecinoDto(null, null, null, null);

    // Sesión
    private VecinoDto vecinoLogueado;

    @PostConstruct
    public void init() {
        Map<String, Object> sessionMap = getSessionMap();
        this.vecinoLogueado = (VecinoDto) sessionMap.get("vecinoLogueado");
    }

    public String login() {
        try {
            var vecinos = vecinoService.obtenerTodo();
            var vecinoOpt = vecinos.stream()
                    .filter(v -> v.email().equals(email))
                    .findFirst();

            if (vecinoOpt.isPresent()) {
                this.vecinoLogueado = vecinoOpt.get();
                getSessionMap().put("vecinoLogueado", vecinoLogueado);
                addMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Inicio de sesión exitoso");
                return "/vecino/dashboard?faces-redirect=true";
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email no encontrado");
                return null;
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al iniciar sesión");
            return null;
        }
    }

    public String registrar() {
        try {
            vecinoService.guardarVecino(nuevoVecino);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro exitoso");
            return "/vecino/login?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            return null;
        }
    }

    public String logout() {
        getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/public/home?faces-redirect=true";
    }

    private Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}