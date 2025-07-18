package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Representa un ítem dentro de un carrito, compuesto por un producto y una cantidad.
 */
public class ItemCarrito implements Serializable {
    /** Producto asociado al ítem. */
    private Producto producto;
    /** Cantidad del producto en el carrito. */
    private int cantidad;
    /**
     * Constructor que inicializa el ítem con un producto y una cantidad.
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    /** @param producto Producto a asignar al ítem. */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    /** @param cantidad Cantidad a asignar al ítem. */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /** @return Producto del ítem. */
    public Producto getProducto() {
        return producto;
    }
    /** @return Cantidad del producto en el ítem. */
    public int getCantidad() {
        return cantidad;
    }
    /** @return Subtotal del ítem (precio * cantidad). */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
    /** @return Representación en texto del ítem. */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }

}

