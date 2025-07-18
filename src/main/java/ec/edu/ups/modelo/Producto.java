package ec.edu.ups.modelo;

import java.io.Serializable;

public class Producto implements Serializable {
    private int codigo;
    private String nombre;
    private double precio;

    public Producto(int codigo, String nombre, double precio) {
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

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }

}