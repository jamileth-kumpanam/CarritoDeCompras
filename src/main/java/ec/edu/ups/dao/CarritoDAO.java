package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.List;
/**
 * Interfaz que define las operaciones CRUD para la entidad Carrito.
 * Las implementaciones pueden variar en el mecanismo de almacenamiento.
 */
public interface CarritoDAO {
    /**
     * Crea un nuevo carrito en el sistema.
     * @param carrito Objeto Carrito a ser creado.
     */
    void crear(Carrito carrito);
    /**
     * Busca un carrito por su código único.
     * @param codigo Código identificador del carrito.
     * @return El objeto Carrito si se encuentra, o null si no existe.
     */
    Carrito buscarPorCodigo(int codigo);
    /**
     * Actualiza la información de un carrito existente.
     * @param carrito Objeto Carrito con los datos actualizados.
     */
    void actualizar(Carrito carrito);
    /**
     * Elimina un carrito del sistema usando su código.
     * @param codigo Código identificador del carrito a eliminar.
     */
    void eliminar(int codigo);
    /**
     * Guarda un carrito en el sistema. Puede crear uno nuevo o actualizar uno existente.
     * @param carrito Objeto Carrito a guardar.
     */
    void guardar(Carrito carrito);
    /**
     * Lista todos los carritos registrados en el sistema.
     * @return Lista de todos los carritos.
     */
    List<Carrito> listarTodos();

}