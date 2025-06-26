package ec.edu.ups.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas(){
        loginView.getIniciarSesiónButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        loginView.getRegistrarseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
                loginView.mostrarMensaje("Usuario registrado");
                limpiar();
            }
        });

    }

    private void autenticar(){
        String username = loginView.getUsuarioText().getText();
        String contrasenia = loginView.getContraText().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.dispose();
        }
    }
    private void limpiar(){
        loginView.getUsuarioText().setText("");
        loginView.getContraText().setText("");
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
    public void registrarUsuario(){
        String username = loginView.getUsuarioText().getText();
        String contrasenia = loginView.getContraText().getText();

        Usuario usuarioNuevo= new Usuario(username, contrasenia, Rol.USUARIO);
        usuarioDAO.crear(usuarioNuevo);
    }
}
