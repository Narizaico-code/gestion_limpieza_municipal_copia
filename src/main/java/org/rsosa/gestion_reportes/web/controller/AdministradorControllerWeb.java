package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.rsosa.gestion_reportes.dominio.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;



    @Component("administradorControllerWeb")
    @Scope("session")
    @Data
    public class AdministradorControllerWeb implements Serializable {

        @Autowired
        private AdministradorService administradorService;

        // Login
        private String usuario;
        private String password;

        // Sesión
        private AdministradorDto adminLogueado;

        @PostConstruct
        public void init() {
            Map<String, Object> sessionMap = getSessionMap();
            this.adminLogueado = (AdministradorDto) sessionMap.get("adminLogueado");
        }

        public String login() {
            try {
                var admins = administradorService.obtenerTodo();
                var adminOpt = admins.stream()
                        .filter(a -> a.username().equals(usuario) && a.password().equals(password))
                        .findFirst();

                if (adminOpt.isPresent()) {
                    this.adminLogueado = adminOpt.get();
                    getSessionMap().put("adminLogueado", adminLogueado);
                    addMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Inicio de sesión exitoso");
                    return "/admin/dashboard?faces-redirect=true";
                } else {
                    addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales incorrectas");
                    return null;
                }
            } catch (Exception e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al iniciar sesión");
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
