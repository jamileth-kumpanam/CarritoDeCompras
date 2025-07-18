package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class LoginView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JButton btnOlvidoContrasenia;
    private JComboBox<String> cbxIdioma;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblIdioma;

    private UsuarioController usuarioController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public LoginView(MensajeInternacionalizacionHandler handler,
                     UsuarioController usuarioController,
                     ProductoController productoController,
                     CarritoController carritoController) {
        this.mensajeHandler = handler;
        this.usuarioController = usuarioController;

        inicializarComponentes();
        configurarEventos(productoController, carritoController);
    }

    private void inicializarComponentes() {
        setTitle(mensajeHandler.get("login.titulo"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setContentPane(panelPrincipal);

        if (cbxIdioma != null) {
            cbxIdioma.removeAllItems();
            cbxIdioma.addItem("Español");
            cbxIdioma.addItem("English");
            cbxIdioma.addItem("Français");
        }

        actualizarTextos();
    }

    private void configurarEventos(ProductoController productoController, CarritoController carritoController) {
        if (cbxIdioma != null) {
            cbxIdioma.addActionListener(this::cambiarIdioma);
        }

        if (btnIniciarSesion != null) {
            btnIniciarSesion.addActionListener(e -> {
                String usuario = txtUsername.getText();
                String contrasenia = new String(txtContrasenia.getPassword());

                if (usuario.isEmpty() || contrasenia.isEmpty()) {
                    mostrarMensaje(mensajeHandler.get("login.campos_vacios"));
                    return;
                }

                Usuario usuarioAutenticado = usuarioController.autenticarYObtenerUsuario(usuario, contrasenia);

                if (usuarioAutenticado != null) {
                    MenuPrincipalView menu = new MenuPrincipalView(
                            usuarioController,
                            productoController,
                            carritoController,
                            mensajeHandler
                    );
                    menu.setVisible(true);
                    dispose();
                } else {
                    mostrarMensaje(mensajeHandler.get("login.error.usuario_contrasenia"));
                }
            });
        }

        if (btnRegistrarse != null) {
            btnRegistrarse.addActionListener(e -> {
                UsuarioRegistroView registroView = new UsuarioRegistroView(usuarioController, mensajeHandler);
                registroView.setVisible(true);
            });
        }

        if (btnOlvidoContrasenia != null) {
            btnOlvidoContrasenia.addActionListener(e -> {
                PreguntasContraseniaView preguntasView = new PreguntasContraseniaView(mensajeHandler, usuarioController, "olvido");
                preguntasView.setVisible(true);
            });
        }
    }

    private void cambiarIdioma(ActionEvent e) {
        String idioma = Objects.requireNonNull(cbxIdioma.getSelectedItem()).toString();

        switch (idioma) {
            case "Español" -> mensajeHandler.setLenguaje("es", "EC");
            case "English" -> mensajeHandler.setLenguaje("en", "US");
            case "Français" -> mensajeHandler.setLenguaje("fr", "FR");
        }

        actualizarTextos();
    }

    private void actualizarTextos() {
        setTitle(mensajeHandler.get("login.titulo"));
        lblIdioma.setText(mensajeHandler.get("login.idioma"));
        lblUsuario.setText(mensajeHandler.get("login.usuario"));
        lblContrasenia.setText(mensajeHandler.get("login.contrasena"));
        btnIniciarSesion.setText(mensajeHandler.get("login.boton"));
        btnRegistrarse.setText(mensajeHandler.get("login.registro"));
        btnOlvidoContrasenia.setText(mensajeHandler.get("login.olvido"));

        revalidate();
        repaint();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Getters y Setters

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JButton getBtnOlvidoContrasenia() {
        return btnOlvidoContrasenia;
    }

    public void setBtnOlvidoContrasenia(JButton btnOlvidoContrasenia) {
        this.btnOlvidoContrasenia = btnOlvidoContrasenia;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }
}