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
    private Usuario usuarioEnProceso;

    private final UsuarioDAO usuarioDAO;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private LoginView loginView;

    public UsuarioController(UsuarioDAO usuarioDAO, MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.mensajeHandler = mensajeHandler;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            JOptionPane.showMessageDialog(loginView,
                    mensajeHandler.get("login.error.usuario_contrasenia"),
                    mensajeHandler.get("login.error.titulo"),
                    JOptionPane.ERROR_MESSAGE);
        } else {
            loginView.dispose();
        }
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public Usuario getUsuarioEnProceso() {
        return usuarioEnProceso;
    }

    public void setPreguntasSeguridadActual(String r1, String r2, String r3, String p1, String p2, String p3) {
        if (usuario != null) {
            usuario.setPregunta1(p1);
            usuario.setPregunta2(p2);
            usuario.setPregunta3(p3);
            usuario.setRespuesta1(r1);
            usuario.setRespuesta2(r2);
            usuario.setRespuesta3(r3);
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
                respuesta1 != null && respuesta2 != null && respuesta3 != null &&
                respuesta1.equals(usuario.getRespuesta1()) &&
                respuesta2.equals(usuario.getRespuesta2()) &&
                respuesta3.equals(usuario.getRespuesta3());
    }

    public void registrarUsuario(String nombre, String contrasenia, String nacimiento, String telefono, String correo, String usuarioNombre) {
        if (nombre.isEmpty() || contrasenia.isEmpty() || nacimiento.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || usuarioNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    mensajeHandler.get("registro.campos.requeridos"),
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
                mensajeHandler.get("registro.exitoso"),
                mensajeHandler.get("ventana.registro.titulo"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void setUsuarioEnProceso(Usuario usuarioEnProceso) {
        this.usuarioEnProceso = usuarioEnProceso;
    }

    public boolean autenticarUsuario(String usuario, String contrasenia) {

        return false;
    }

    public Usuario autenticarYObtenerUsuario(String usuario, String contrasenia) {
        Usuario usuarioAutenticado = usuarioDAO.autenticar(usuario, contrasenia);
        if (usuarioAutenticado != null) {
            this.usuario = usuarioAutenticado;
            return usuarioAutenticado;
        }
        return null;
    }

    public boolean recuperarContrasenia(String username, String respuesta1, String respuesta2, String respuesta3, String nuevaContrasenia) {
        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null &&
                usuario.getRespuesta1() != null && usuario.getRespuesta1().equals(respuesta1) &&
                usuario.getRespuesta2() != null && usuario.getRespuesta2().equals(respuesta2) &&
                usuario.getRespuesta3() != null && usuario.getRespuesta3().equals(respuesta3)) {
            usuario.setContrasenia(nuevaContrasenia);
            usuarioDAO.actualizar(usuario);
            return true;
        }
        return false;
    }
}
