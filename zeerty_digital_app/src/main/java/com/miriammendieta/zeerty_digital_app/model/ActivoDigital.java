package com.miriammendieta.zeerty_digital_app.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTIVOS_DIGITALES")
public class ActivoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo")
    private Integer id_activo;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fecha_alta;

    @Column(name = "fecha_caducidad")
    private LocalDate fecha_caducidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoActivo estado;

    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;

    // ===== CONSTRUCTOR VACÍO =====
    public ActivoDigital() {}

    // ===== GETTERS Y SETTERS =====

    public Integer getId_activo() {
        return id_activo;
    }

    public void setId_activo(Integer id_activo) {
        this.id_activo = id_activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public LocalDate getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(LocalDate fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public EstadoActivo getEstado() {
        return estado;
    }

    public void setEstado(EstadoActivo estado) {
        this.estado = estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}