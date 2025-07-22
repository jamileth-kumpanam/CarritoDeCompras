package ec.edu.ups.vista;

import ec.edu.ups.controlador.*;
import ec.edu.ups.dao.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Internacionalización
            Locale locale = new Locale("es", "EC");
            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler(locale);

            // Componentes
            String[] tipos = {"Texto", "Binario"};
            JComboBox<String> comboTipo = new JComboBox<>(tipos);
            JTextField campoRuta = new JTextField(30);
            JLabel etiquetaTipo = new JLabel("Seleccione el tipo de almacenamiento:");
            JLabel etiquetaRuta = new JLabel("Ruta de almacenamiento:");

            // Panel de formulario
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            panel.add(etiquetaTipo);
            panel.add(Box.createVerticalStrut(5));
            panel.add(comboTipo);
            panel.add(Box.createVerticalStrut(10));
            panel.add(etiquetaRuta);
            panel.add(Box.createVerticalStrut(5));
            panel.add(campoRuta);
            panel.add(Box.createVerticalStrut(10));

            // Mostrar ventana de configuración
            int resultado = JOptionPane.showConfirmDialog(null, panel, "Configuración inicial", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Debe completar la configuración.");
                System.exit(0);
            }

            // Obtener datos
            String tipoAlmacenamiento = comboTipo.getSelectedItem().toString();
            String ruta = campoRuta.getText().trim();

            if (ruta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una ruta.");
                System.exit(0);
            }

            File carpeta = new File(ruta);
            if (!carpeta.exists() || !carpeta.isDirectory()) {
                JOptionPane.showMessageDialog(null, "La ruta ingresada no existe o no es válida.");
                System.exit(0);
            }

            // Crear DAOs
            UsuarioDAO usuarioDAO = DAOFactory.crearUsuarioDAO(tipoAlmacenamiento, ruta);
            ProductoDAO productoDAO = DAOFactory.crearProductoDAO(tipoAlmacenamiento, ruta);
            CarritoDAO carritoDAO = DAOFactory.crearCarritoDAO(tipoAlmacenamiento, ruta);

            // Controladores
            UsuarioController usuarioController = new UsuarioController(usuarioDAO, mensajeHandler);
            ProductoController productoController = new ProductoController(productoDAO);
            CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, null, mensajeHandler);

            // Vista principal
            LoginView loginView = new LoginView(mensajeHandler, usuarioController, productoController, carritoController);
            loginView.setVisible(true);
        });
    }
}
