package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDemo {
    public static void main(String[] args) {
        try {
            // Guardar usuarios
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario(1, "Juan", "juan123", "clave123"));
            UsuarioIO.guardarUsuariosEnBinario(usuarios, "usuarios.bin");

            // Leer usuarios
            List<Usuario> usuariosLeidos = UsuarioIO.leerUsuariosDeBinario("usuarios.bin");
            for (Usuario u : usuariosLeidos) {
                System.out.println(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}