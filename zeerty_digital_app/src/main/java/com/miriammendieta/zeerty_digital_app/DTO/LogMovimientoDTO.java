package com.miriammendieta.zeerty_digital_app.DTO;

import java.time.LocalDateTime;

public class LogMovimientoDTO {

    private Integer id;
    private String usuario;
    private String rolUsuario;
    private String accion;
    private String activoNombre;
    private LocalDateTime fechaHora;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getRolUsuario() { return rolUsuario; }
    public void setRolUsuario(String rolUsuario) { this.rolUsuario = rolUsuario; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getActivoNombre() { return activoNombre; }
    public void setActivoNombre(String activoNombre) { this.activoNombre = activoNombre; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
