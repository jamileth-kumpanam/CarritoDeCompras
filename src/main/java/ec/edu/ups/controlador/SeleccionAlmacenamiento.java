package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOTexto;
import ec.edu.ups.dao.impl.UsuarioDAOBinario;

public class SeleccionAlmacenamiento {
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