package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

public class Carrito implements Serializable {
    private final double IVA = 0.12;
    private static int contador = 1;
    private int codigo;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;

    public Carrito() {
        this.codigo = contador++;
        this.fechaCreacion = new GregorianCalendar();
        this.items = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

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

    public void vaciarCarrito() {
        items.clear();
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIVA() {
        return calcularSubtotal() * IVA;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    public static Carrito desdeString(String linea) {
        String[] partes = linea.split(";");
        int codigo = Integer.parseInt(partes[0]);
        long fechaMillis = Long.parseLong(partes[1]);
        Carrito carrito = new Carrito();
        carrito.setCodigo(codigo);
        java.util.GregorianCalendar fecha = new java.util.GregorianCalendar();
        fecha.setTimeInMillis(fechaMillis);
        carrito.setFechaCreacion(fecha);
        // No se cargan los items aquí
        return carrito;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaCreacion.getTime() +
                ", items=" + items +
                '}';
    }
}