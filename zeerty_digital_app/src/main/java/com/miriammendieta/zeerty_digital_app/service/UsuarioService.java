package com.miriammendieta.zeerty_digital_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miriammendieta.zeerty_digital_app.model.Estado;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.repository.UsuarioRepository;

/**
 * Servicio que contiene la lógica de negocio relacionada con los usuarios.
 * Inyección por constructor.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * LOGIN
     * 1. Busca el usuario por email.
     * 2. Si no existe, devuelve null.
     * 3. Si existe, compara la contraseña.
     * 4. Si coincide, devuelve el usuario.
     * 5. Si no coincide, devuelve null.
     */
    public Usuario login(String email, String password) {

        // findByEmail devuelve Optional<Usuario>
        Optional<Usuario> uOpt = repo.findByEmail(email);

        // Si no existe --> login inválido
        if (uOpt.isEmpty()) {
            return null;
        }

        // Obtener el usuario real
        Usuario u = uOpt.get();

        // Comparar contraseña cifrada con la introducida
        if (passwordEncoder.matches(password, u.getPasswordHash())) {
            return u;
        }

        return null;
    }

    /**
     * CREAR USUARIO
     */
    public Usuario crearUsuario(String nombre, String apellidos, String email,
            String passwordHash, String telefono,
            Rol rol, Estado estado) {

        // Encripta la contraseña ANTES de crear el usuario
        String passwordEncriptada = passwordEncoder.encode(passwordHash);

        Usuario nuevo = new Usuario(nombre, apellidos, email, passwordEncriptada, telefono, rol, estado);
        return repo.save(nuevo);
    }

    /**
     * LISTAR TODOS LOS USUARIOS
     */
    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    /**
     * BUSCAR POR ID
     */
    public Usuario buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    /**
     * BUSCAR POR EMAIL
     * Devuelve Usuario o null.
     */
    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    /**
     * ELIMINAR USUARIO
     */
    public void eliminarUsuario(Integer id) {
        repo.deleteById(id);
    }

    /**
     * DESACTIVAR USUARIO
     */
    public void desactivarUsuario(Integer id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setEstado(Estado.INACTIVO);
            repo.save(u);
        }
    }

    /**
     * ACTIVAR USUARIO
     */
    public void activarUsuario(Integer id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setEstado(Estado.ACTIVO);
            repo.save(u);
        }
    }

    /**
     * MODIFICAR USUARIO
     */
    public void modificarUsuario(Usuario usuarioForm) {

        Usuario usuarioBD = repo.findById(usuarioForm.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioBD.setNombre(usuarioForm.getNombre());
        usuarioBD.setApellidos(usuarioForm.getApellidos());
        usuarioBD.setEmail(usuarioForm.getEmail());
        usuarioBD.setTelefono(usuarioForm.getTelefono());
        usuarioBD.setRol(usuarioForm.getRol());
        usuarioBD.setEstado(usuarioForm.getEstado());

        // Solo cifrar si el usuario escribió una nueva contraseña
        if (usuarioForm.getPasswordHash() != null && !usuarioForm.getPasswordHash().isBlank()) {
            usuarioBD.setPasswordHash(passwordEncoder.encode(usuarioForm.getPasswordHash()));
        }

        repo.save(usuarioBD);
    }

}
