package com.miriammendieta.zeerty_digital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import java.util.Optional;

/**
 * Repositorio JPA para acceder a la tabla USUARIOS.
 *
 * JpaRepository proporciona métodos CRUD ya preparados:
 * - findAll()
 * - findById()
 * - save()
 * - delete()
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su email.
     * Spring Data JPA genera automáticamente la consulta:
     * SELECT * FROM usuarios WHERE email = ?
     *
     * Se devuelve Optional<Usuario> para manejar correctamente el caso
     * en el que el usuario no exista.
     */
    Optional<Usuario> findByEmail(String email);
}
