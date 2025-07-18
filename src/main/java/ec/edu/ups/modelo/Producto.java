package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Lista todos los productos almacenados.
 * @return Lista de productos.
 */
public class Producto implements Serializable {
    /**
     * Código único del producto.
     */
    private int codigo;

    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * Precio del producto.
     */
    private double precio;
    /**
     * Constructor de Producto.
     * @param codigo Código del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(int codigo, String nombre, double precio) {

        /**
         * Obtiene el código del producto.
         * @return Código del producto.
         */
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public static Producto desdeString(String linea) {
        String[] partes = linea.split(";");
        int codigo = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        double precio = Double.parseDouble(partes[2]);
        return new Producto(codigo, nombre, precio);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Crea un producto a partir de una cadena de texto.
     * @param linea Cadena con los datos del producto.
     * @return Producto creado.
     */
    public double getPrecio() {
        return precio;
    }

    @Override
    /**
     * Obtiene el precio del producto.
     * @return Precio del producto.
     */
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }

}