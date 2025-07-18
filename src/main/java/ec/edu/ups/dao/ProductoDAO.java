package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Interfaz para el acceso a datos de productos.
 */
public interface ProductoDAO {
    /**
     * Guarda un producto.
     * @param producto Producto a guardar.
     */
    void crear(Producto producto);
    /**
     * Busca un producto por su código.
     * @param codigo Código del producto.
     * @return Producto encontrado o null.
     */
    Producto buscarPorCodigo(int codigo);
    /**
     * Busca productos por nombre.
     * @param nombre Nombre a buscar.
     * @return Lista de productos encontrados.
     */
    List<Producto> buscarPorNombre(String nombre);
    /**
     * Actualiza un producto existente.
     * @param producto Producto actualizado.
     */
    void actualizar(Producto producto);
    /**
     * Elimina un producto por su código.
     * @param codigo Código del producto a eliminar.
     */
    void eliminar(int codigo);
    /**
     * Lista todos los productos almacenados.
     * @return Lista de productos.
     */
    List<Producto> listarTodos();

}