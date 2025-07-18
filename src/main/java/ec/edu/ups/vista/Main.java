package ec.edu.ups.vista;

import ec.edu.ups.controlador.*;
import ec.edu.ups.dao.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.Locale;
/**
 * Clase principal que inicia la aplicación Swing.
 * Configura el tipo de almacenamiento y muestra la ventana de login.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Locale locale = new Locale("es", "EC");
            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler(locale);

            String[] opciones = {"Memoria", "Archivo", "Binario"};
            String tipoAlmacenamiento = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el tipo de almacenamiento:",
                    "Configuración",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (tipoAlmacenamiento == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de almacenamiento.");
                System.exit(0);
            }

            UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO(tipoAlmacenamiento);
            ProductoDAO productoDAO = DAOFactory.crearProductoDAO(tipoAlmacenamiento);
            CarritoDAO carritoDAO = DAOFactory.crearCarritoDAO(tipoAlmacenamiento);

            UsuarioController usuarioController = new UsuarioController(usuarioDAO, mensajeHandler);
            ProductoController productoController = new ProductoController(productoDAO);
            CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, null, mensajeHandler);

            LoginView loginView = new LoginView(mensajeHandler, usuarioController, productoController, carritoController);
            loginView.setVisible(true);
        });
    }
}
