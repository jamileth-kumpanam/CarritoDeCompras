package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioRegistroView extends JInternalFrame {

    private JPanel RegistroUsuario;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrarme;
    private JTextField txtNacimiento;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtUsuario;
    private JPanel Registro;
    private JLabel lblRegistrarse;
    private JLabel lblNombres;
    private JLabel lblContrasenia;
    private JLabel lblNacimiento;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblUsuario;

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
        setContentPane(RegistroUsuario);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        setTitle(mensajeHandler.get("ventana.registro.titulo"));
        lblRegistrarse.setText(mensajeHandler.get("ventana.registro.titulo"));
        lblNombres.setText(mensajeHandler.get("ventana.registro.nombre"));
        lblContrasenia.setText(mensajeHandler.get("ventana.registro.contrasenia"));
        lblNacimiento.setText(mensajeHandler.get("ventana.registro.nacimiento"));
        lblTelefono.setText(mensajeHandler.get("ventana.registro.telefono"));
        lblCorreo.setText(mensajeHandler.get("ventana.registro.correo"));
        lblUsuario.setText(mensajeHandler.get("ventana.registro.usuario"));
        btnRegistrarme.setText(mensajeHandler.get("ventana.registro.boton"));


        btnRegistrarme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.registrarUsuario(
                        txtNombre.getText(),
                        new String(txtContrasenia.getPassword()),
                        txtNacimiento.getText(),
                        txtTelefono.getText(),
                        txtCorreo.getText(),
                        txtUsuario.getText()
                );
            }
        });
    }

    // Getters
    public JPanel getRegistroUsuario() {
        return RegistroUsuario;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JTextField getTxtNacimiento() {
        return txtNacimiento;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JButton getRegistrarmeButton() {
        return btnRegistrarme;
    }

    // Setters
    public void setRegistroUsuario(JPanel registroUsuario) {
        this.RegistroUsuario = registroUsuario;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public void setTxtNacimiento(JTextField txtNacimiento) {
        this.txtNacimiento = txtNacimiento;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public void setRegistrarmeButton(JButton registrarmeButton) {
        this.btnRegistrarme = registrarmeButton;
    }

    // Métodos útiles
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JButton getBtnRegistrarme() {
        return btnRegistrarme;
    }

    public void setBtnRegistrarme(JButton btnRegistrarme) {
        this.btnRegistrarme = btnRegistrarme;
    }

    public JPanel getRegistro() {
        return Registro;
    }

    public void setRegistro(JPanel registro) {
        Registro = registro;
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtContrasenia.setText("");
        txtNacimiento.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
    }
}
