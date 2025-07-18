package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.UsuarioRegistroView;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.vista.PreguntasContraseniaView;
/**
 * Controlador para el registro de nuevos usuarios.
 */
public class RegistroController{
    private UsuarioDAO usuarioDAO;
    private UsuarioRegistroView registroView;
    /**
     * Controlador de usuario.
     */
    private UsuarioController usuarioController;

    /**
     * Constructor del controlador de registro.
     * @param usuarioDAO DAO de usuario.
     * @param registroView Vista de registro.
     * @param usuarioController Controlador de usuario.
     */
    public RegistroController(UsuarioDAO usuarioDAO, UsuarioRegistroView registroView, UsuarioController usuarioController) {
        /**
         * DAO para operaciones de usuario.
         */
        this.usuarioDAO = usuarioDAO;
        this.registroView = registroView;
        this.usuarioController = usuarioController;
        configurarEventos();
    }
    /**
     * Configura los eventos de la vista de registro.
     */
    private void configurarEventos() {
        registroView.getBtnRegistrarme().addActionListener(e -> registrarUsuario());
    }
    /**
     * Registra un nuevo usuario y muestra la vista de preguntas de seguridad.
     */
    private void registrarUsuario() {
        String username = registroView.getTxtNombre().getText();
        String contrasena = new String(registroView.getTxtContrasenia().getPassword());

        Usuario nuevoUsuario = new Usuario(username, contrasena, Rol.USUARIO);

        System.out.println("Registrando usuario: " + nuevoUsuario);
        usuarioDAO.guardar(nuevoUsuario);

        PreguntasContraseniaView preguntasView = new PreguntasContraseniaView(
                usuarioController.getMensajeInternacionalizacionHandler(), usuarioController, "registro"
        );
        preguntasView.setVisible(true);
        registroView.dispose();
    }
}
