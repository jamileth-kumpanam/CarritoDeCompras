package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOTexto;
import ec.edu.ups.dao.impl.UsuarioDAOBinario;
/**
 * Clase utilitaria para seleccionar el tipo de almacenamiento de usuarios.
 */
public class SeleccionAlmacenamiento {
    /**
     * Selecciona e instancia el DAO de usuario según el tipo de almacenamiento.
     * @param tipo Tipo de almacenamiento ("memoria", "texto", "binario").
     * @param rutaArchivo Ruta del archivo si aplica.
     * @return Instancia de UsuarioDAO correspondiente.
     * @throws IllegalArgumentException Si el tipo no es soportado.
     */
    public static UsuarioDAO seleccionarDAO(String tipo, String rutaArchivo) {
        switch (tipo) {
            case "memoria":
                return new UsuarioDAOMemoria();
            case "texto":
                return new UsuarioDAOTexto(rutaArchivo);
            case "binario":
                return new UsuarioDAOBinario(rutaArchivo);
            default:
                throw new IllegalArgumentException("Tipo de almacenamiento no soportado");
        }
    }
}