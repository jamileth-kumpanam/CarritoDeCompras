package ec.edu.ups.dao;

import ec.edu.ups.dao.impl.*;

public class DAOFactory {

    public static UsuarioDAO crearUsuarioDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new UsuarioDAOMemoria();
            case "archivo" -> new UsuarioDAOArchivo("usuarios.txt");
            case "binario" -> new UsuarioDAOBinario("usuarios.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }

    public static ProductoDAO crearProductoDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new ProductoDAOMemoria();
            case "archivo" -> new ProductoDAOArchivo("productos.txt");
            case "binario" -> new ProductoDAOBinario("productos.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }

    public static CarritoDAO crearCarritoDAO(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "memoria" -> new CarritoDAOMemoria();
            case "archivo" -> new CarritoDAOArchivo("carritos.txt");
            case "binario" -> new CarritoDAOBinario("carritos.dat");
            default -> throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipo);
        };
    }
}