import ec.edu.ups.dao.impl.UsuarioDAOArchivo;
import ec.edu.ups.modelo.Usuario;

public class PruebaCrearUsuario {
    public static void main(String[] args) {
        String archivoUsuarios = "usuarios.txt";

        UsuarioDAOArchivo usuarioDAO = new UsuarioDAOArchivo(archivoUsuarios);

        Usuario nuevoUsuario = new Usuario(1, "Juan Perez", "juanp", "1234");

        usuarioDAO.crear(nuevoUsuario);

        System.out.println("Usuario creado exitosamente.");
    }
}