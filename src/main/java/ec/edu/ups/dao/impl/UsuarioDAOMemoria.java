package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de UsuarioDAO que almacena los usuarios en memoria.
 * Utiliza una lista interna para gestionar los usuarios durante la ejecución.
 */
public class UsuarioDAOMemoria implements UsuarioDAO {
    /** Lista interna de usuarios en memoria. */
    private List<Usuario> usuarios = new ArrayList<>();
    /**
     * Constructor que inicializa la lista con usuarios por defecto.
     */
    public UsuarioDAOMemoria() {
        crear(new Usuario("admin", "12345", Rol.ADMINISTRADOR));
        crear(new Usuario("user", "12345", Rol.USUARIO));
    }
    /**
     * Autentica un usuario verificando nombre de usuario y contraseña.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o null si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                System.out.println("Usuario autenticado con rol: " + usuario.getRol());
                return usuario;
            }
        }
        return null;
    }
    /**
     * Agrega un nuevo usuario a la lista en memoria.
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }
    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Elimina un usuario de la lista por su nombre de usuario.
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }
    /**
     * Actualiza la información de un usuario existente.
     * @param usuario Usuario con datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAux = usuarios.get(i);
            if (usuarioAux.getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }
    /**
     * Devuelve la lista de todos los usuarios en memoria.
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }
    /**
     * Lista los usuarios que tienen un rol específico.
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol dado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosEncontrados.add(usuario);
            }
        }

        return usuariosEncontrados;
    }
    /**
     * Guarda un usuario en la lista (agrega uno nuevo).
     * @param nuevoUsuario Usuario a guardar.
     */
    @Override
    public void guardar(Usuario nuevoUsuario) {
        System.out.println("Guardando usuario con rol: " + nuevoUsuario.getRol());
        usuarios.add(nuevoUsuario);
    }
}
