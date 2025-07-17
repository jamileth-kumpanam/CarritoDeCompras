package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class UsuarioRegistroView extends JInternalFrame implements Idioma {

    private JPanel RegistroUsuario;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrarme;
    private JTextField txtNacimiento;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtUsuario;
    private JComboBox<String> cbxGenero;
    private JLabel lblRegistrarse;
    private JLabel lblNombres;
    private JLabel lblContrasenia;
    private JLabel lblNacimiento;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblUsuario;
    private JLabel lblGenero;

    private UsuarioController usuarioController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioRegistroView(UsuarioController usuarioController, MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;
        this.usuarioController = usuarioController;

        setContentPane(RegistroUsuario);
        setTitle(mensajeHandler.get("registro.titulo"));
        setSize(400, 400);
        setClosable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegistrarme.addActionListener((ActionEvent e) -> registrarUsuario());

        actualizarTextos(mensajeHandler.getBundle());
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword()).trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String nacimiento = txtNacimiento.getText().trim();
        String genero = (String) cbxGenero.getSelectedItem();

        if (nombre.isEmpty() || usuario.isEmpty() || contrasenia.isEmpty()) {
            mostrarMensaje("Por favor, llena los campos obligatorios.");
            return;
        }

        usuarioController.registrarUsuario(
                nombre,
                contrasenia,
                nacimiento,
                telefono,
                correo,
                usuario
        );

        mostrarMensaje("Registro exitoso.");
        limpiarCampos();
        dispose();
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtNacimiento.setText("");
        cbxGenero.setSelectedIndex(0);
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        setTitle(mensajeHandler.get("registro.titulo"));
        lblRegistrarse.setText(mensajeHandler.get("registro.titulo"));
        lblNombres.setText(mensajeHandler.get("registro.nombre"));
        lblUsuario.setText(mensajeHandler.get("registro.usuario"));
        lblContrasenia.setText(mensajeHandler.get("registro.contrasena"));
        lblCorreo.setText(mensajeHandler.get("registro.correo"));
        lblTelefono.setText(mensajeHandler.get("registro.telefono"));
        lblNacimiento.setText(mensajeHandler.get("registro.nacimiento"));
        lblGenero.setText(mensajeHandler.get("registro.genero"));
        btnRegistrarme.setText(mensajeHandler.get("registro.boton"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JButton getBtnRegistrarme() {
        return btnRegistrarme;
    }

    public void setBtnRegistrarme(JButton btnRegistrarme) {
        this.btnRegistrarme = btnRegistrarme;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }


}
