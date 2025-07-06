package ec.edu.ups.vista;

import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class PreguntasContraseniaView extends JInternalFrame implements Idioma {

    private MensajeInternacionalizacionHandler mensajeHandler;
    private UsuarioController usuarioController;
    private String modo;

    private JPanel PreguntasDeSeguridad;
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
    private JLabel lblP;

    public PreguntasContraseniaView(MensajeInternacionalizacionHandler mensajeHandler,
                                    UsuarioController usuarioController,
                                    String modo) {
        this.mensajeHandler = mensajeHandler;
        this.usuarioController = usuarioController;
        this.modo = modo;

        setTitle(mensajeHandler.get("preguntas.titulo"));
        setContentPane(PreguntasDeSeguridad);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setClosable(true);
        setResizable(true);

        actualizarTextos(mensajeHandler.getBundle());

        btnGuardarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarRespuestas();
            }
        });
    }

    private void procesarRespuestas() {
        // Para ejemplo, usamos solo tres preguntas. Ajusta según tu UI
        String p1 = lblP1.getText();
        String p2 = lblP2.getText();
        String p3 = lblP3.getText();

        String r1 = txtNomMama.getText().trim();
        String r2 = txtNomMascota.getText().trim();
        String r3 = txtCiudadNacimiento.getText().trim();

        if (r1.isEmpty() || r2.isEmpty() || r3.isEmpty()) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("preguntas.error.faltan"));
            return;
        }

        if (modo.equals("registro")) {
            usuarioController.setPreguntasSeguridadActual(r1, r2, r3, p1, p2, p3);
            usuarioController.getUsuarioDAO().guardar(usuarioController.getUsuarioEnProceso());

            JOptionPane.showMessageDialog(this, mensajeHandler.get("preguntas.guardadas"));

            LoginView loginView = new LoginView(mensajeHandler);
            UsuarioController nuevoUC = new UsuarioController(usuarioController.getUsuarioDAO(), loginView, mensajeHandler);
            loginView.setUsuarioController(nuevoUC);
            loginView.setVisible(true);
            dispose();

        } else if (modo.equals("recuperacion")) {
            boolean verificado = usuarioController.verificarPreguntas(p1, r1, p2, r2, p3, r3);

            if (verificado) {
                JOptionPane.showMessageDialog(this, mensajeHandler.get("verificacion.correcta"));

                CambiarContrasenaView cambiarView = new CambiarContrasenaView(usuarioController, mensajeHandler);
                cambiarView.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, mensajeHandler.get("verificacion.incorrecta"));
            }
        }
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        lblP1.setText(mensajeHandler.get("pregunta1"));
        lblP2.setText(mensajeHandler.get("pregunta2"));
        lblP3.setText(mensajeHandler.get("pregunta3"));
        lblP4.setText(mensajeHandler.get("pregunta4"));
        lblP5.setText(mensajeHandler.get("pregunta5"));
        lblP6.setText(mensajeHandler.get("pregunta6"));
        lblP7.setText(mensajeHandler.get("pregunta7"));
        lblP8.setText(mensajeHandler.get("pregunta8"));
        lblP9.setText(mensajeHandler.get("pregunta9"));
        lblP10.setText(mensajeHandler.get("pregunta10"));
        lblP.setText(mensajeHandler.get("preguntas.titulo"));
        btnGuardarDatos.setText(mensajeHandler.get("boton.guardar"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
