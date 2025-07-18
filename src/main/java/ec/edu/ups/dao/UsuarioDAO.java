package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import java.util.List;
/**
 * Interfaz que define las operaciones CRUD y de autenticación para la entidad Usuario.
 * Implementaciones pueden variar en el mecanismo de almacenamiento (memoria, archivo, binario, etc).
 */
public interface UsuarioDAO {
    /**
     * Autentica un usuario verificando su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario a autenticar.
     * @param contrasenia Contraseña correspondiente al usuario.
     * @return El objeto Usuario autenticado si las credenciales son correctas, o null si no coinciden.
     */
    Usuario autenticar(String username, String contrasenia);
    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario Objeto Usuario a ser creado.
     */
    void crear(Usuario usuario);
    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return El objeto Usuario si se encuentra, o null si no existe.
     */
    Usuario buscarPorUsername(String username);
    /**
     * Elimina un usuario del sistema usando su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario a eliminar.
     */
    void eliminar(String username);
    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario Objeto Usuario con los datos actualizados.
     */
    void actualizar(Usuario usuario);
    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return Lista de todos los usuarios.
     */
    List<Usuario> listarTodos();
    /**
     * Lista los usuarios que tienen un rol específico.
     *
     * @param rol Rol por el cual filtrar los usuarios.
     * @return Lista de usuarios que tienen el rol especificado.
     */
    List<Usuario> listarPorRol(Rol rol);
    /**
     * Guarda un usuario en el sistema. Puede crear uno nuevo o actualizar uno existente.
     *
     * @param nuevoUsuario Objeto Usuario a guardar.
     */
    void guardar(Usuario nuevoUsuario);
}