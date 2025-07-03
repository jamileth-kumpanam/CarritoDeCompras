package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioRegistroView extends JInternalFrame {

    private JPanel RegistroUsuario;
    private JTextField txtNombre;
    private JTextField txtContrasenia;
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

    public JPanel getRegistroUsuario() {
        return RegistroUsuario;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getRegistrarmeButton() {
        return registrarmeButton;
    }

    public void setRegistroUsuario(JPanel registroUsuario) {
        RegistroUsuario = registroUsuario;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void setTxtContrasenia(JTextField txtContrasenia) {
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
