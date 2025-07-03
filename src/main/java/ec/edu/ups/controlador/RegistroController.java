package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.UsuarioRegistroView;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.vista.PreguntasContraseniaView;

public class RegistroController{
    private UsuarioDAO usuarioDAO;
    private UsuarioRegistroView registroView;
    private UsuarioController usuarioController;


    public RegistroController(UsuarioDAO usuarioDAO, UsuarioRegistroView registroView, UsuarioController usuarioController) {
        this.usuarioDAO = usuarioDAO;
        this.registroView = registroView;
        this.usuarioController = usuarioController;
        configurarEventos();
    }

    private void configurarEventos() {
        registroView.getBtnRegistrarse().addActionListener(e -> registrarUsuario());
    }

    private void registrarUsuario() {
        String username = registroView.getTxtNombre().getText();
        String contrasena = new String(registroView.getTxtContraseña().getPassword());

        Usuario nuevoUsuario = new Usuario(username, contrasena, Rol.USUARIO);

        System.out.println("Registrando usuario: " + nuevoUsuario);
        usuarioDAO.guardar(nuevoUsuario);

        PreguntasContraseniaView preguntasView = new PreguntasContraseniaView(
                usuarioController.getMensajeHandler(), usuarioController, "registro"
        );
        preguntasView.setVisible(true);
        registroView.dispose();
    }

}
}
