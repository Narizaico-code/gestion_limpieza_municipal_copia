package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.service.AdministradorService;
import org.rsosa.gestion_reportes.dominio.service.VecinoService;
import org.rsosa.gestion_reportes.persistence.AdministradorMunicipalidadEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("loginControllerWeb")
@Scope("view")
@Data
public class LoginControllerWeb implements Serializable {

    @Autowired
    private VecinoService vecinoService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private AdministradorMunicipalidadEntityRepository adminMunicRepository;

    private String emailOUsername;
    private String password;
    private String mensajeExito;
    private String mensajeError;

    public void iniciarSesion() {
        if (emailOUsername == null || password == null ||
                emailOUsername.trim().isEmpty() || password.trim().isEmpty()) {
            mostrarError("Por favor complete todos los campos");
            return;
        }

        var vecinos = vecinoService.obtenerTodo();
        for (VecinoDto vecino : vecinos) {
            if (vecino.email().equals(emailOUsername.trim())) {
                if (vecino.password().equals(password.trim())){
                    iniciarSesionVecino(vecino);
                    return;
                }
            }
        }

        var admins = administradorService.obtenerTodo();
        for (AdministradorDto admin : admins) {
            if (admin.username().equals(emailOUsername.trim())) {
                // No validamos password
                iniciarSesionAdmin(admin);
                return;
            }
        }

        mostrarError("Usuario no encontrado");
    }

    private void iniciarSesionVecino(VecinoDto vecino) {
        HttpSession session = getSession();
        session.setAttribute("vecinoLogueado", vecino);
        session.setAttribute("tipoUsuario", "vecino");

        mostrarExito("¡Bienvenido " + vecino.nameNeighbor() + "!");
        PrimeFaces.current().executeScript(
                "setTimeout(function(){ window.location.href = 'vecino/menuVecino.xhtml'; }, 1000);"
        );
    }

    private void iniciarSesionAdmin(AdministradorDto admin) {
        HttpSession session = getSession();
        session.setAttribute("adminLogueado", admin);
        session.setAttribute("tipoUsuario", "admin");

        var adminMunic = adminMunicRepository.obtenerPorAdministrador(admin.adminId());
        if (!adminMunic.isEmpty()) {
            String zona = adminMunic.get(0).municipalidad().zone();
            session.setAttribute("zonaAdmin", zona);
        }

        mostrarExito("¡Bienvenido Administrador " + admin.fullName() + "!");
        PrimeFaces.current().executeScript(
                "setTimeout(function(){ window.location.href = 'admin/menuAdmin.xhtml'; }, 1500);"
        );
    }

    private void mostrarExito(String mensaje) {
        this.mensajeExito = mensaje;
        PrimeFaces.current().executeScript("PF('modalExito').show()");
    }

    private void mostrarError(String mensaje) {
        this.mensajeError = mensaje;
        PrimeFaces.current().executeScript("PF('modalError').show()");
    }

    private HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public void limpiarFormulario() {
        this.emailOUsername = "";
        this.password = "";
        this.mensajeError = null;
        this.mensajeExito = null;
    }
}