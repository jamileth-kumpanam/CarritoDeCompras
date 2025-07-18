package ec.edu.ups.dao;

import ec.edu.ups.dao.impl.*;
/**
 * Fábrica de DAOs para la creación de instancias según el tipo de almacenamiento.
 * Permite obtener implementaciones de UsuarioDAO, ProductoDAO y CarritoDAO
 * en memoria, archivo de texto o archivo binario.
 */
public class DAOFactory {
    /**
     * Crea una instancia de UsuarioDAO según el tipo de almacenamiento especificado.
     *
     * @param tipo Tipo de almacenamiento: "memoria", "archivo" o "binario".
     * @return Instancia de UsuarioDAO correspondiente al tipo.
     * @throws IllegalArgumentException Si el tipo no es soportado.
     */
    public static UsuarioDAO crearUsuarioDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new UsuarioDAOMemoria();
            case "archivo" -> new UsuarioDAOArchivo("usuarios.txt");
            case "binario" -> new UsuarioDAOBinario("usuarios.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }
    /**
     * Crea una instancia de ProductoDAO según el tipo de almacenamiento especificado.
     *
     * @param tipo Tipo de almacenamiento: "memoria", "archivo" o "binario".
     * @return Instancia de ProductoDAO correspondiente al tipo.
     * @throws IllegalArgumentException Si el tipo no es soportado.
     */
    public static ProductoDAO crearProductoDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new ProductoDAOMemoria();
            case "archivo" -> new ProductoDAOArchivo("productos.txt");
            case "binario" -> new ProductoDAOBinario("productos.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }
    /**
     * Crea una instancia de CarritoDAO según el tipo de almacenamiento especificado.
     *
     * @param tipo Tipo de almacenamiento: "memoria", "archivo" o "binario".
     * @return Instancia de CarritoDAO correspondiente al tipo.
     * @throws IllegalArgumentException Si el tipo no es soportado.
     */
    public static CarritoDAO crearCarritoDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new CarritoDAOMemoria();
            case "archivo" -> new CarritoDAOArchivo("carritos.txt");
            case "binario" -> new CarritoDAOBinario("carritos.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }
}