package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de UsuarioDAO que almacena usuarios en un archivo de texto.
 * Cada usuario se guarda en una línea con el formato: username;contrasenia
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {
    private final File archivo;

    /**
     * Constructor que recibe la ruta base y crea el archivo si no existe.
     * @param ruta Carpeta base para el archivo.
     */
    public UsuarioDAOArchivoTexto(String ruta) {
        this.archivo = new File(ruta + File.separator + "usuarios.txt");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de usuarios", e);
        }
    }

    /**
     * Autentica un usuario por username y contraseña.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : listarTodos()) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Guarda un nuevo usuario en el archivo.
     */
    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(usuario.getUsername() + ";" + usuario.getContrasenia());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuario", e);
        }
    }

    /**
     * Busca un usuario por su username.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : listarTodos()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un usuario existente.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarTodos(usuarios);
    }

    /**
     * Elimina un usuario por su username.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarTodos(usuarios);
    }

    /**
     * Lista todos los usuarios almacenados en el archivo.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    // Usa el constructor con username, contrasenia y rol (null)
                    usuarios.add(new Usuario(partes[0], partes[1], null));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer usuarios", e);
        }
        return usuarios;
    }

    /**
     * Lista los usuarios por rol (no implementado).
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        // Implementar si es necesario
        return List.of();
    }

    /**
     * Método no implementado para guardar usuario.
     */
    @Override
    public void guardar(Usuario nuevoUsuario) {
        // No implementado
    }

    /**
     * Guarda la lista completa de usuarios en el archivo, sobrescribiendo el contenido.
     */
    private void guardarTodos(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getUsername() + ";" + usuario.getContrasenia());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuarios", e);
        }
    }
}