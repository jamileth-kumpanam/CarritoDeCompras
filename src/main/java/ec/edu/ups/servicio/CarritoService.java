package ec.edu.ups.servicio;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de un carrito de compras.
 */
public interface CarritoService {
    /**
     * Agrega un producto al carrito con la cantidad especificada.
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    void agregarProducto(Producto producto, int cantidad);
    /**
     * Elimina un producto del carrito por su código.
     * @param codigoProducto Código del producto a eliminar.
     */
    void eliminarProducto(int codigoProducto);
    /** Elimina todos los productos del carrito. */
    void vaciarCarrito();
    /**
     * Calcula el total a pagar por los productos en el carrito.
     * @return Total a pagar.
     */
    double calcularTotal();
    /**
     * Obtiene la lista de ítems (productos y cantidades) en el carrito.
     * @return Lista de ítems del carrito.
     */
    List<ItemCarrito> obtenerItems();
    /**
     * Verifica si el carrito está vacío.
     * @return true si está vacío, false en caso contrario.
     */
    boolean estaVacio();
}
