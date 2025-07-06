package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JButton btnContra;
    private JComboBox<String> cbxIdioma;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblIdioma;

    private UsuarioController usuarioController;

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    private MensajeInternacionalizacionHandler mensajeHandler;

    public LoginView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;

        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setContentPane(panelPrincipal);

        if (cbxIdioma.getItemCount() == 0) {
            cbxIdioma.addItem("Español");
            cbxIdioma.addItem("English");
            cbxIdioma.addItem("Français");
        }

        cbxIdioma.addActionListener(this::cambiarIdioma);

        cambiarIdioma(new ActionEvent(cbxIdioma, ActionEvent.ACTION_PERFORMED, ""));
    }

    private void cambiarIdioma(ActionEvent e) {
        String idioma = (String) cbxIdioma.getSelectedItem();

        if ("Español".equals(idioma)) {
            mensajeHandler.setLenguaje("es", "EC");
        } else if ("English".equals(idioma)) {
            mensajeHandler.setLenguaje("en", "US");
        } else if ("Français".equals(idioma)) {
            mensajeHandler.setLenguaje("fr", "FR");
        }

        actualizarTextos();
    }

    private void actualizarTextos() {
        setTitle(mensajeHandler.get("login.titulo"));
        lblUsuario.setText(mensajeHandler.get("login.usuario"));
        lblContrasenia.setText(mensajeHandler.get("login.contrasena"));
        btnIniciarSesion.setText(mensajeHandler.get("login.boton"));
        btnRegistrarse.setText(mensajeHandler.get("login.registro"));
        lblIdioma.setText(mensajeHandler.get("login.idioma"));
        btnContra.setText(mensajeHandler.get("login.olvide"));
        revalidate();
        repaint();
        System.out.println("Textos actualizados al idioma: " + mensajeHandler.getLocale());
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JComboBox<String> getCbxIdioma() {
        return cbxIdioma;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JButton getBtnContra() {
        return btnContra;
    }

    public void setCbxIdioma(JComboBox<String> cbxIdioma) {
        this.cbxIdioma = cbxIdioma;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JLabel getLblContrasenia() {
        return lblContrasenia;
    }

    public JLabel getLblIdioma() {
        return lblIdioma;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void setLblIdioma(JLabel lblIdioma) {
        this.lblIdioma = lblIdioma;
    }

    public void setLblContrasenia(JLabel lblContrasenia) {
        this.lblContrasenia = lblContrasenia;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public void setBtnContra(JButton btnContra) {
        this.btnContra = btnContra;
    }
}