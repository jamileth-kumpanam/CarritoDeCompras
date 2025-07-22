package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de UsuarioDAO que almacena los usuarios en un archivo binario.
 */
public class UsuarioDAOBinario implements UsuarioDAO {

    private final File archivo;

    /**
     * Constructor que recibe la ruta base y usa "usuarios.bin" dentro de esa ruta.
     * @param rutaBase Ruta donde se almacenará el archivo binario.
     */
    public UsuarioDAOBinario(String rutaBase) {
        File carpeta = new File(rutaBase);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        this.archivo = new File(carpeta, "usuarios.bin");
        precargarUsuarios(); // opcional, quita esta línea si no quieres precarga
    }

    /**
     * Precarga usuarios básicos en el archivo si no existe o está vacío.
     */
    private void precargarUsuarios() {
        if (archivo.exists() && archivo.length() > 0) return; // Ya hay datos

        List<Usuario> usuariosIniciales = new ArrayList<>();
        usuariosIniciales.add(new Usuario(1, "Administrador", "admin", "1234", Rol.ADMINISTRADOR));
        usuariosIniciales.add(new Usuario(2, "Usuario Básico", "user", "1234", Rol.USUARIO));
        guardarUsuarios(usuariosIniciales);
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listar()) {
            if (usuario.getCodigo() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listar() {
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodigo() == usuario.getCodigo()) {
                usuarios.set(i, usuario);
                guardarUsuarios(usuarios);
                return;
            }
        }
        // Si no existía, agregar nuevo
        crear(usuario);
    }

    public void eliminar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getCodigo() == id);
        guardarUsuarios(usuarios);
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        return listar();
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosPorRol = new ArrayList<>();
        for (Usuario usuario : listar()) {
            if (usuario.getRol() != null && usuario.getRol().equals(rol)) {
                usuariosPorRol.add(usuario);
            }
        }
        return usuariosPorRol;
    }

    @Override
    public void guardar(Usuario nuevoUsuario) {
        Usuario existente = buscarPorUsername(nuevoUsuario.getUsername());
        if (existente == null) {
            crear(nuevoUsuario);
        } else {
            actualizar(nuevoUsuario);
        }
    }

    private void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
