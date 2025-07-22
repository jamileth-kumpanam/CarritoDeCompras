package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de UsuarioDAO que almacena los usuarios en un archivo de texto.
 */
public class UsuarioDAOArchivo implements UsuarioDAO {

    private final String archivo;

    public UsuarioDAOArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : listar()) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listar()) {
            if (usuario.getCodigo() == id) {
                return usuario;
            }
        }
        return null;
    }

    public void eliminar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getCodigo() == id);
        guardarUsuarios(usuarios);
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                usuarios.add(Usuario.desdeString(linea));
            }
        } catch (IOException e) {
            // Puede que no exista el archivo aún, no panic
        }
        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodigo() == usuario.getCodigo()) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        return listar();
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario usuario : listar()) {
            if (usuario.getRol() != null && usuario.getRol().equals(rol)) {
                filtrados.add(usuario);
            }
        }
        return filtrados;
    }

    @Override
    public void guardar(Usuario usuario) {
        Usuario existente = buscarPorUsername(usuario.getUsername());
        if (existente == null) {
            crear(usuario);
        } else {
            actualizar(usuario);
        }
    }

    private void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario u : usuarios) {
                // Considera agregar el rol si quieres persistirlo
                writer.write(u.getCodigo() + ";" + u.getNombre() + ";" + u.getUsername() + ";" + u.getContrasenia());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
