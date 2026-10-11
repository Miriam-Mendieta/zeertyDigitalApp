package com.miriammendieta.zeerty_digital_app.model;

import jakarta.persistence.*; // Importa las anotaciones de JPA (Jakarta Persistence API)

/**
 * Esta clase representa la tabla USUARIOS de tu base de datos MySQL.
 * Spring Boot la usa para mapear automáticamente cada columna a un atributo
 * Java.
 */
@Entity
@Table(name = "usuarios") // Nombre EXACTO de la tabla en MySQL
public class Usuario {

  /**
   * IDUsuario es la clave primaria de la tabla.
   * 
   * @Id indica que es el identificador único.
   * @GeneratedValue con IDENTITY hace que MySQL genere el valor automáticamente
   *                 (AUTO_INCREMENT).
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IDUsuario")
  private Integer idUsuario;

  @Column(name = "Nombre", nullable = false)
  private String nombre;

  @Column(name = "Apellidos", nullable = false)
  private String apellidos;

  /**
   * Columna Email (NOT NULL y UNIQUE).
   * unique = true indica que no puede haber dos usuarios con el mismo email.
   */
  @Column(name = "Email", nullable = false, unique = true)
  private String email;

  @Column(name = "PasswordHash", nullable = false)
  private String passwordHash;

  @Column(name = "Telefono")
  private String telefono;

  @Enumerated(EnumType.STRING)
  @Column(name = "Rol", nullable = false)
  private Rol rol;

  @Enumerated(EnumType.STRING)
  @Column(name = "Estado", nullable = false)
  private Estado estado;

  /**
     * Constructor vacío OBLIGATORIO para que JPA / Hibernate pueda 
     * instanciar la entidad al consultar la base de datos.
     */
    public Usuario() {
    }

    /**
     * Constructor para crear nuevos usuarios.
     */
    public Usuario(String nombre, String apellidos, String email, String passwordHash, String telefono, Rol rol, Estado estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.passwordHash = passwordHash;
        this.telefono = telefono;
        this.rol = rol;
        this.estado = estado;
    }

  // ------------------ GETTERS Y SETTERS ------------------
  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }
}
