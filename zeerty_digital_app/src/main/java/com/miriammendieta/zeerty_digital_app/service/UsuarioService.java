package com.miriammendieta.zeerty_digital_app.service;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.model.Estado;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN
    public Usuario login(String email, String password) {
        Optional<Usuario> uOpt = repo.findByEmail(email);
        if (uOpt.isEmpty()) return null;

        Usuario u = uOpt.get();
        return passwordEncoder.matches(password, u.getPasswordHash()) ? u : null;
    }

    // COMPROBAR SI EL EMAIL YA EXISTE
    public boolean emailExiste(String email) {
        return repo.findByEmail(email).isPresent();
    }

    // CREAR USUARIO
    public Usuario crearUsuario(String nombre, String apellidos, String email,
                                String passwordHash, String telefono,
                                Rol rol, Estado estado) {

        if (emailExiste(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        String passwordEncriptada = passwordEncoder.encode(passwordHash);

        Usuario nuevo = new Usuario(nombre, apellidos, email,
                passwordEncriptada, telefono, rol, estado);

        return repo.save(nuevo);
    }

    // LISTAR TODOS
    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    // ELIMINAR
    public void eliminarUsuario(Integer id) {
        repo.deleteById(id);
    }

    // DESACTIVAR
    public void desactivarUsuario(Integer id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setEstado(Estado.INACTIVO);
            repo.save(u);
        }
    }

    // ACTIVAR
    public void activarUsuario(Integer id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setEstado(Estado.ACTIVO);
            repo.save(u);
        }
    }

    // MODIFICAR
    public void modificarUsuario(Usuario usuarioForm) {

        Usuario usuarioBD = repo.findById(usuarioForm.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validación de email duplicado
        Usuario existente = buscarPorEmail(usuarioForm.getEmail());
        if (existente != null && !existente.getIdUsuario().equals(usuarioForm.getIdUsuario())) {
            throw new RuntimeException("El email ya está registrado por otro usuario");
        }

        usuarioBD.setNombre(usuarioForm.getNombre());
        usuarioBD.setApellidos(usuarioForm.getApellidos());
        usuarioBD.setEmail(usuarioForm.getEmail());
        usuarioBD.setTelefono(usuarioForm.getTelefono());
        usuarioBD.setRol(usuarioForm.getRol());
        usuarioBD.setEstado(usuarioForm.getEstado());

        if (usuarioForm.getPasswordHash() != null && !usuarioForm.getPasswordHash().isBlank()) {
            usuarioBD.setPasswordHash(passwordEncoder.encode(usuarioForm.getPasswordHash()));
        }

        repo.save(usuarioBD);
    }
}
