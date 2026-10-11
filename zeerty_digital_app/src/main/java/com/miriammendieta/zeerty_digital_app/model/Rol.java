package com.miriammendieta.zeerty_digital_app.model;

/**
 * Enum que representa los valores posibles del campo Rol en la tabla USUARIOS.
 * Se guarda como texto en la base de datos gracias a @Enumerated(EnumType.STRING).
 */
public enum Rol {
    ADMINISTRACION,
    AUDITORIA,
    USUARIO_APP,
    INVITADO
}
