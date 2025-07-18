package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controlador para la gestión de usuarios, autenticación y registro.
 */
public class UsuarioController {
    /**
     * Usuario autenticado actualmente.
     */
    private Usuario usuario;
    /**
     * Usuario en proceso de registro o modificación.
     */
    private Usuario usuarioEnProceso;

    /**
     * DAO para operaciones de usuario.
     */
    private final UsuarioDAO usuarioDAO;
    /**
     * Manejador de mensajes internacionalizados.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;
    /**
     * Vista de inicio de sesión.
     */
    private LoginView loginView;
    /**
     * Constructor del controlador de usuario.
     * @param usuarioDAO DAO de usuario.
     * @param mensajeHandler Manejador de mensajes.
     */
    public UsuarioController(UsuarioDAO usuarioDAO, MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.mensajeHandler = mensajeHandler;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
        configurarEventosEnVistas();
    }
    /**
     * Configura los eventos de la vista de login.
     */
    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
    }
    /**
     * Realiza la autenticación del usuario.
     */
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
    /**
     * Obtiene el DAO de usuario.
     * @return UsuarioDAO.
     */
    public Usuario getUsuarioEnProceso() {
        return usuarioEnProceso;
    }
    /**
     * Obtiene el usuario en proceso.
     * @return Usuario en proceso.
     */
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
    /**
     * Asigna las preguntas y respuestas de seguridad al usuario autenticado.
     * @param r1 Respuesta 1.
     * @param r2 Respuesta 2.
     * @param r3 Respuesta 3.
     * @param p1 Pregunta 1.
     * @param p2 Pregunta 2.
     * @param p3 Pregunta 3.
     */
    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
    /**
     * Obtiene el usuario autenticado.
     * @return Usuario autenticado.
     */
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeHandler;
    }
    /**
     * Obtiene el manejador de mensajes.
     * @return MensajeInternacionalizacionHandler.
     */
    public boolean cambiarContrasenia(String actual, String nueva) {
        if (usuario != null && usuario.getContrasenia().equals(actual)) {
            usuario.setContrasenia(nueva);
            usuarioDAO.actualizar(usuario);
            return true;
        }
        return false;
    }
    /**
     * Cambia la contraseña del usuario autenticado.
     * @param actual Contraseña actual.
     * @param nueva Nueva contraseña.
     * @return true si se cambió correctamente.
     */
    public boolean verificarRespuestas(String respuesta1, String respuesta2, String respuesta3) {
        return usuario != null &&
                respuesta1 != null && respuesta2 != null && respuesta3 != null &&
                respuesta1.equals(usuario.getRespuesta1()) &&
                respuesta2.equals(usuario.getRespuesta2()) &&
                respuesta3.equals(usuario.getRespuesta3());
    }
    /**
     * Verifica las respuestas de seguridad del usuario.
     * @param respuesta1 Respuesta 1.
     * @param respuesta2 Respuesta 2.
     * @param respuesta3 Respuesta 3.
     * @return true si todas coinciden.
     */
    public void registrarUsuario(String nombre, String contrasenia, String nacimiento, String telefono, String correo, String usuarioNombre) {
        if (nombre.isEmpty() || contrasenia.isEmpty() || nacimiento.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || usuarioNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    mensajeHandler.get("registro.campos.requeridos"),
                    mensajeHandler.get("ventana.registro.titulo"),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
/**
 * Registra un nuevo usuario con los datos proporcionados.
 * @param nombre Nombre.
 * @param contrasenia Contraseña.
 * @param nacimiento Fecha de nacimiento.
 * @param telefono Teléfono.
 * @param correo Correo electrónico.
 * @param usuarioNombre Nombre de usuario.
 */
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
    /**
     * Asigna el usuario en proceso.
     * @param usuarioEnProceso Usuario.
     */
    public void setUsuarioEnProceso(Usuario usuarioEnProceso) {
        this.usuarioEnProceso = usuarioEnProceso;
    }
    /**
     * Autentica un usuario (no implementado).
     * @param usuario Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return false siempre.
     */
    public boolean autenticarUsuario(String usuario, String contrasenia) {

        return false;
    }
    /**
     * Autentica y retorna el usuario si es válido.
     * @param usuario Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o null.
     */
    public Usuario autenticarYObtenerUsuario(String usuario, String contrasenia) {
        Usuario usuarioAutenticado = usuarioDAO.autenticar(usuario, contrasenia);
        if (usuarioAutenticado != null) {
            this.usuario = usuarioAutenticado;
            return usuarioAutenticado;
        }
        return null;
    }
    /**
     * Recupera la contraseña si las respuestas de seguridad son correctas.
     * @param username Nombre de usuario.
     * @param respuesta1 Respuesta 1.
     * @param respuesta2 Respuesta 2.
     * @param respuesta3 Respuesta 3.
     * @param nuevaContrasenia Nueva contraseña.
     * @return true si se recuperó correctamente.
     */
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
