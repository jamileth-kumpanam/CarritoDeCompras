package ec.edu.ups.dao;

import ec.edu.ups.dao.impl.*;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

/**
 * Fábrica de DAOs para la creación de instancias según el tipo de almacenamiento y ruta base.
 */
public class DAOFactory {

    /**
     * Crea una instancia de UsuarioDAO según el tipo de almacenamiento y ruta base.
     *
     * @param tipo    Tipo de almacenamiento: "texto" o "binario".
     * @param rutaBase Carpeta base para almacenar archivos.
     * @return Instancia de UsuarioDAO.
     */
    public static UsuarioDAO crearUsuarioDAO(String tipo, String rutaBase) {
        String archivoUsuario;
        switch (tipo.toLowerCase()) {
            case "texto":
                archivoUsuario = rutaBase + "/usuarios.txt";
                UsuarioDAOArchivo usuarioDAOTexto = new UsuarioDAOArchivo(archivoUsuario);
                precargarUsuariosTexto(usuarioDAOTexto);
                return usuarioDAOTexto;
            case "binario":
                archivoUsuario = rutaBase + "/usuarios.bin";
                UsuarioDAOBinario usuarioDAOBinario = new UsuarioDAOBinario(archivoUsuario);
                precargarUsuariosBinario(usuarioDAOBinario);
                return usuarioDAOBinario;
            default:
                throw new IllegalArgumentException("Tipo de almacenamiento no soportado para UsuarioDAO: " + tipo);
        }
    }

    /**
     * Crea una instancia de ProductoDAO según el tipo de almacenamiento y ruta base.
     *
     * @param tipo    Tipo de almacenamiento: "texto" o "binario".
     * @param rutaBase Carpeta base para almacenar archivos.
     * @return Instancia de ProductoDAO.
     */
    public static ProductoDAO crearProductoDAO(String tipo, String rutaBase) {
        switch (tipo.toLowerCase()) {
            case "texto":
                return new ProductoDAOArchivo(rutaBase);
            case "binario":
                return new ProductoDAOBinario("productos.bin"); // El constructor debe ajustar la ruta dentro, o cambia a rutaBase + "/productos.bin"
            default:
                throw new IllegalArgumentException("Tipo de almacenamiento no soportado para ProductoDAO: " + tipo);
        }
    }

    /**
     * Crea una instancia de CarritoDAO según el tipo de almacenamiento y ruta base.
     *
     * @param tipo    Tipo de almacenamiento: "texto" o "binario".
     * @param rutaBase Carpeta base para almacenar archivos.
     * @return Instancia de CarritoDAO.
     */
    public static CarritoDAO crearCarritoDAO(String tipo, String rutaBase) {
        switch (tipo.toLowerCase()) {
            case "texto":
                return new CarritoDAOArchivo(rutaBase + "/carritos.txt");
            case "binario":
                return new CarritoDAOBinario(rutaBase + "/carritos.bin");
            default:
                throw new IllegalArgumentException("Tipo de almacenamiento no soportado para CarritoDAO: " + tipo);
        }
    }

    /* Métodos privados para precargar usuarios en texto y binario */

    private static void precargarUsuariosTexto(UsuarioDAOArchivo dao) {
        if (dao.listarTodos().isEmpty()) {
            dao.crear(new Usuario(1, "Administrador", "admin", "1234", Rol.ADMINISTRADOR));
            dao.crear(new Usuario(2, "Usuario Básico", "user", "1234", Rol.USUARIO));
        }
    }

    private static void precargarUsuariosBinario(UsuarioDAOBinario dao) {
        if (dao.listarTodos().isEmpty()) {
            dao.crear(new Usuario(1, "Administrador", "admin", "1234", Rol.ADMINISTRADOR));
            dao.crear(new Usuario(2, "Usuario Básico", "user", "1234", Rol.USUARIO));
        }
    }
}
