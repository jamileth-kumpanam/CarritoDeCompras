package ec.edu.ups.servicio;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de CarritoService que gestiona el carrito de compras en memoria.
 */
public class CarritoServiceImpl implements CarritoService {
    /** Lista interna de ítems en el carrito. */
    private final List<ItemCarrito> items;
    /** Constructor que inicializa la lista de ítems vacía. */
    public CarritoServiceImpl() {
        items = new ArrayList<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void vaciarCarrito() {
        items.clear();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemCarrito> obtenerItems() {
        return items;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean estaVacio() {
        return items.isEmpty();
    }
}

