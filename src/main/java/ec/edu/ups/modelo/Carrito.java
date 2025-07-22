package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Representa un carrito de compras con productos, fecha de creación, usuario y cálculo de totales.
 */
public class Carrito implements Serializable {
    /** Porcentaje de IVA aplicado a las compras. */
    private final double IVA = 0.12;
    /** Contador estático para asignar códigos únicos a cada carrito. */
    private static int contador = 1;
    /** Código único del carrito. */
    private int codigo;
    /** Fecha de creación del carrito. */
    private GregorianCalendar fechaCreacion;
    /** Lista de ítems (productos y cantidades) en el carrito. */
    private List<ItemCarrito> items;
    /** Usuario dueño del carrito. */
    private Usuario usuario;

    /**
     * Constructor que inicializa el carrito con un código único y la fecha actual.
     */
    public Carrito() {
        this.codigo = contador++;
        this.fechaCreacion = new GregorianCalendar();
        this.items = new ArrayList<>();
    }

    /**
     * Constructor que inicializa el carrito con fecha, ítems y usuario.
     * @param fechaCreacion Fecha de creación.
     * @param items Lista de ítems.
     * @param usuario Usuario dueño del carrito.
     */
    public Carrito(GregorianCalendar fechaCreacion, List<ItemCarrito> items, Usuario usuario) {
        this.codigo = contador++;
        this.fechaCreacion = fechaCreacion;
        this.items = items;
        this.usuario = usuario;
    }

    /** @return Código único del carrito. */
    public int getCodigo() {
        return codigo;
    }

    /** @param codigo Código a asignar al carrito. */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /** @return Fecha de creación del carrito. */
    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /** @param fechaCreacion Fecha a asignar al carrito. */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /** @return Lista de ítems en el carrito. */
    public List<ItemCarrito> getItems() {
        return items;
    }

    /** @param items Lista de ítems a asignar al carrito. */
    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    /** @return Usuario dueño del carrito. */
    public Usuario getUsuario() {
        return usuario;
    }

    /** @param usuario Usuario a asignar al carrito. */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Agrega un producto al carrito con la cantidad especificada.
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito por su código.
     * @param codigoProducto Código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            ItemCarrito item = it.next();
            if (item.getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /** Elimina todos los productos del carrito. */
    public void vaciarCarrito() {
        items.clear();
    }

    /** @return true si el carrito está vacío, false en caso contrario. */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /** @return Suma de los subtotales de todos los ítems (sin IVA). */
    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /** @return Valor del IVA calculado sobre el subtotal. */
    public double calcularIVA() {
        return calcularSubtotal() * IVA;
    }

    /** @return Total a pagar (subtotal + IVA). */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Devuelve la fecha de creación del carrito en formato dd/MM/yyyy.
     * @return Fecha formateada como String.
     */
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaCreacion.getTime());
    }

    /**
     * Crea un objeto Carrito a partir de una línea de texto.
     * @param linea Línea con los datos del carrito.
     * @return Carrito creado.
     */
    public static Carrito desdeString(String linea) {
        String[] partes = linea.split(";");
        int codigo = Integer.parseInt(partes[0]);
        long fechaMillis = Long.parseLong(partes[1]);
        Carrito carrito = new Carrito();
        carrito.setCodigo(codigo);
        GregorianCalendar fecha = new GregorianCalendar();
        fecha.setTimeInMillis(fechaMillis);
        carrito.setFechaCreacion(fecha);
        // No se cargan los items aquí
        return carrito;
    }

    /** @return Representación en texto del carrito. */
    @Override
    public String toString() {
        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaCreacion.getTime() +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                ", items=" + items +
                '}';
    }
}