package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Validaciones;
import ec.edu.ups.util.excepciones.CedulaInvalidaException;
import ec.edu.ups.util.excepciones.PasswordInvalidaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class CambioContraseniaView extends JInternalFrame implements Idioma {

    private JPanel CambioDeContraseña;
    private JTextField txtActualPassword;
    private JTextField txtNewPassword;
    private JButton btnContraseniaNueva;
    private JButton btnCancelar;
    private JLabel lblContraseniaActual;
    private JLabel lblModificar;
    private JLabel lblTitulo;

    private final MensajeInternacionalizacionHandler mensajeHandler;

    public CambioContraseniaView(UsuarioController usuarioController, MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(CambioDeContraseña);
        setTitle(mensajeHandler.get("cambioContrasena.titulo"));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);

        actualizarTextos(mensajeHandler.getBundle());

        btnContraseniaNueva.addActionListener((ActionEvent e) -> {
            String cedula = txtActualPassword.getText().trim();
            String nueva = txtNewPassword.getText().trim();

            try {
                Validaciones.validarCedula(cedula);
                Validaciones.validarPassword(nueva);

                boolean actualizada = usuarioController.cambiarContrasenia(cedula, nueva);
                if (actualizada) {
                    mostrarMensaje(mensajeHandler.get("cambioContrasena.exito"));
                    limpiarCampos();
                } else {
                    mostrarMensaje(mensajeHandler.get("cambioContrasena.error"));
                }
            } catch (CedulaInvalidaException | PasswordInvalidaException ex) {
                mostrarMensaje(ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    public void actualizarTextos(ResourceBundle bundle) {
        lblTitulo.setText(mensajeHandler.get("cambioContrasena.titulo"));
        lblContraseniaActual.setText(mensajeHandler.get("cambioContrasena.actual"));
        lblModificar.setText(mensajeHandler.get("cambioContrasena.nueva"));
        btnContraseniaNueva.setText(mensajeHandler.get("cambioContrasena.boton"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtActualPassword.setText("");
        txtNewPassword.setText("");
    }

    public JPanel getPanelPrincipal() {
        return CambioDeContraseña;
    }

    public JTextField getTxtActualPassword() {
        return txtActualPassword;
    }

    public JTextField getTxtNewPassword() {
        return txtNewPassword;
    }

    public JButton getBtnActualizar() {
        return btnContraseniaNueva;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}
