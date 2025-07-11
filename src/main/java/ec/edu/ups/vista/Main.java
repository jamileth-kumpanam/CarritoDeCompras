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

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            ProductoDAO productoDAO = new ProductoDAOMemoria();
            CarritoDAO carritoDAO = new CarritoDAOMemoria();

            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");

            LoginView loginView = new LoginView(mensajeHandler);
            UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, mensajeHandler);
            ProductoController productoController = new ProductoController(productoDAO);
            CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
            CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView);

            loginView.setVisible(true);

            loginView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    if (usuarioController.getUsuarioAutenticado() != null) {
                        new MenuPrincipalView(
                                usuarioController,
                                productoController,
                                carritoController,
                                mensajeHandler
                        ).setVisible(true);
                    }
                }
            });
        });
    }
}