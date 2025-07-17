package ec.edu.ups.vista;
import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Locale locale = new Locale("es", "EC");
            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler(locale);

            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            ProductoDAO productoDAO = new ProductoDAOMemoria();
            CarritoDAO carritoDAO = new CarritoDAOMemoria();

            UsuarioController usuarioController = new UsuarioController(usuarioDAO, mensajeHandler);
            ProductoController productoController = new ProductoController(productoDAO);

            CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, null, mensajeHandler);

            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(carritoController, productoController, mensajeHandler);
            carritoController.setCarritoAnadirView(carritoAnadirView);

            LoginView loginView = new LoginView(mensajeHandler, usuarioController, productoController, carritoController);
            usuarioController.setLoginView(loginView);

            loginView.setVisible(true);
        });
    }
}