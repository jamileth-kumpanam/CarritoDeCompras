package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class PreguntasContraseniaView extends JInternalFrame {
    /**
     * Ventana interna para responder preguntas de seguridad y recuperar la contraseña.
     * Permite ingresar respuestas y establecer una nueva contraseña.
     */
    private JPanel PreguntasDeSeguridad;
    private JLabel lblP;
    private JLabel lblP1;
    private JLabel lblP2;
    private JLabel lblP3;
    private JLabel lblP4;
    private JLabel lblP5;
    private JLabel lblP6;
    private JLabel lblP7;
    private JLabel lblP8;
    private JLabel lblP9;
    private JLabel lblP10;
    private JTextField txtNomMama;
    private JTextField txtNomMascota;
    private JTextField txtCiudadNacimiento;
    private JTextField txtMaestro;
    private JTextField txtAmigoInfancia;
    private JTextField txtPeliFavorita;
    private JTextField txtPrimerLibro;
    private JTextField txtBicicleta;
    private JTextField txtPrimerJefe;
    private JTextField txtComidaFav;
    private JButton btnGuardarDatos;

    public PreguntasContraseniaView(MensajeInternacionalizacionHandler mensajeHandler, UsuarioController usuarioController, String titulo) {
        setTitle(mensajeHandler.get("preguntas.titulo"));
        setClosable(true);
        setContentPane(PreguntasDeSeguridad);
        setSize(500, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnGuardarDatos.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Ingrese su usuario:");
            String nuevaContrasenia = JOptionPane.showInputDialog(this, "Ingrese la nueva contraseña:");

            String respuesta1 = txtNomMama.getText();
            String respuesta2 = txtNomMascota.getText();
            String respuesta3 = txtCiudadNacimiento.getText();

            boolean exito = usuarioController.recuperarContrasenia(username, respuesta1, respuesta2, respuesta3, nuevaContrasenia);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Respuestas incorrectas o usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JButton getBtnGuardarDatos() {
        return btnGuardarDatos;
    }

    public void setBtnGuardarDatos(JButton btnGuardarDatos) {
        this.btnGuardarDatos = btnGuardarDatos;
    }
}