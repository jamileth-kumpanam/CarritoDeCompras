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
    /** Ruta del archivo binario donde se almacenan los usuarios. */
    private final String archivo;
    /**
     * Constructor que recibe la ruta del archivo binario.
     * @param archivo Ruta del archivo binario.
     */
    public UsuarioDAOBinario(String archivo) {
        this.archivo = archivo;
    }
    /**
     * Autentica un usuario verificando nombre de usuario y contraseña.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o null si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Crea un nuevo usuario y lo agrega al archivo binario.
     * @param usuario Usuario a crear.
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }
    /**
     * Busca un usuario por su nombre de usuario en el archivo binario.
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Busca un usuario por su id (código) en el archivo binario.
     * @param id Código del usuario.
     * @return Usuario encontrado o null.
     */
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listar()) {
            if (usuario.getCodigo() == id) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Lista todos los usuarios almacenados en el archivo binario.
     * @return Lista de usuarios.
     */
    public List<Usuario> listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    /**
     * Actualiza la información de un usuario existente en el archivo binario.
     * @param usuario Usuario con datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodigo() == usuario.getCodigo()) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }
    /**
     * Elimina un usuario del archivo binario por su id (código).
     * @param id Código del usuario a eliminar.
     */
    public void eliminar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getCodigo() == id);
        guardarUsuarios(usuarios);
    }
    /**
     * Elimina un usuario del archivo binario por su nombre de usuario.
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }
    /**
     * Devuelve la lista de todos los usuarios almacenados en el archivo binario.
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return listar();
    }
    /**
     * Lista los usuarios que tienen un rol específico.
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol dado.
     */
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
    /**
     * Guarda un usuario en el archivo binario (crea uno nuevo).
     * @param nuevoUsuario Usuario a guardar.
     */
    @Override
    public void guardar(Usuario nuevoUsuario) {
        crear(nuevoUsuario);
    }
    /**
     * Guarda la lista de usuarios en el archivo binario.
     * @param usuarios Lista de usuarios a guardar.
     */
    private void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}