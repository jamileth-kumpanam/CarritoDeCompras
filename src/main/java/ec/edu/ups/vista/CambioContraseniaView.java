package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class CambioContraseniaView extends JInternalFrame {

    private JPanel CambioDeContraseña;
    private JTextField txtActualPassword;
    private JTextField txtNewPassword;
    private JButton btnContraseniaNueva;

    public CambioContraseniaView() {
        setContentPane(CambioDeContraseña);
        setTitle("Cambiar Contraseña");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
    }

    public CambioContraseniaView(UsuarioController usuarioController, MensajeInternacionalizacionHandler mensajeHandler) {
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setCambioDeContraseña(JPanel cambioDeContraseña) {
        CambioDeContraseña = cambioDeContraseña;
    }

    public void setTxtActualPassword(JTextField txtActualPassword) {
        this.txtActualPassword = txtActualPassword;
    }

    public void setTxtNewPassword(JTextField txtNewPassword) {
        this.txtNewPassword = txtNewPassword;
    }

    public void setBtnContraseniaNueva(JButton btnContraseniaNueva) {
        this.btnContraseniaNueva = btnContraseniaNueva;
    }

    public void limpiarCampos() {
        txtActualPassword.setText("");
        txtNewPassword.setText("");
    }
}
