package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class UsuarioRegistroView extends JInternalFrame {

    private JPanel RegistroUsuario;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JButton registrarmeButton;

    public UsuarioRegistroView() {
        setContentPane(RegistroUsuario);
        setTitle("Registro de Usuario");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
    }

    public UsuarioRegistroView(UsuarioController usuarioController, MensajeInternacionalizacionHandler mensajeHandler) {
    }

    public JPanel getRegistroUsuario() {
        return RegistroUsuario;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getRegistrarmeButton() {
        return registrarmeButton;
    }

    public void setRegistroUsuario(JPanel registroUsuario) {
        this.RegistroUsuario = registroUsuario;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public void setRegistrarmeButton(JButton registrarmeButton) {
        this.registrarmeButton = registrarmeButton;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtContrasenia.setText("");
    }
}
