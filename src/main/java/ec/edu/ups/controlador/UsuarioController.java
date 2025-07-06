package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {


    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.mensajeHandler = mensajeHandler;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
    }

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            JOptionPane.showMessageDialog(loginView,
                    "Usuario o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            loginView.dispose();
        }
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeHandler;
    }

    public boolean cambiarContrasenia(String actual, String nueva) {
        if (usuario != null && usuario.getContrasenia().equals(actual)) {
            usuario.setContrasenia(nueva);
            usuarioDAO.actualizar(usuario);
            return true;
        }
        return false;
    }

    public boolean verificarRespuestas(String respuesta1, String respuesta2, String respuesta3) {
        return usuario != null &&
                usuario.getRespuesta1().equals(respuesta1) &&
                usuario.getRespuesta2().equals(respuesta2) &&
                usuario.getRespuesta3().equals(respuesta3);
    }

    public void registrarUsuario(String nombre, String contrasenia, String nacimiento, String telefono, String correo, String usuarioNombre) {
        if (nombre.isEmpty() || contrasenia.isEmpty() || nacimiento.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || usuarioNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    mensajeHandler.get("registro.campos.requeridos"),  // Puedes usar "¡Todos los campos son obligatorios!" si aún no tienes esto en el .properties
                    mensajeHandler.get("ventana.registro.titulo"),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasenia(contrasenia);
        nuevoUsuario.setFechaNacimiento(nacimiento);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setUsuario(usuarioNombre);

        usuarioDAO.crear(nuevoUsuario);

        JOptionPane.showMessageDialog(null,
                mensajeHandler.get("registro.exitoso"), // "¡Usuario registrado exitosamente!"
                mensajeHandler.get("ventana.registro.titulo"),
                JOptionPane.INFORMATION_MESSAGE);
    }
}