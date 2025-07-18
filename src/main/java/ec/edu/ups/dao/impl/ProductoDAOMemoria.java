package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de la interfaz ProductoDAO que almacena los productos en memoria.
 * Utiliza una lista interna para gestionar los productos durante la ejecución de la aplicación.
 */
public class ProductoDAOMemoria implements ProductoDAO {
    /** Lista interna que almacena los productos en memoria. */
    private final List<Producto> productos = new ArrayList<>();
    /**
     * Agrega un nuevo producto a la lista en memoria.
     * @param producto Producto a agregar.
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }
    /**
     * Busca un producto por su código en la lista en memoria.
     * @param codigo Código del producto a buscar.
     * @return Producto encontrado o null si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
    /**
     * Busca productos por su nombre (ignorando mayúsculas/minúsculas).
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos que coinciden con el nombre.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    /**
     * Actualiza un producto existente en la lista en memoria.
     * Elimina el producto anterior y agrega el actualizado.
     * @param producto Producto con los datos actualizados.
     */
    @Override
    public void actualizar(Producto producto) {
        eliminar(producto.getCodigo());
        crear(producto);
    }
    /**
     * Elimina un producto de la lista en memoria por su código.
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
    }
    /**
     * Devuelve una copia de la lista de todos los productos en memoria.
     * @return Lista de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }
}
