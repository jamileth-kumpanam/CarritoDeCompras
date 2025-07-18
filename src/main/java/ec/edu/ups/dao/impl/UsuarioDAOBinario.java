package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOBinario implements UsuarioDAO {
    private final String archivo;

    public UsuarioDAOBinario(String archivo) {
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

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listar()) {
            if (usuario.getCodigo() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
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

    public void eliminar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getCodigo() == id);
        guardarUsuarios(usuarios);
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        return listar();
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosPorRol = new ArrayList<>();
        for (Usuario usuario : listar()) {
            if (usuario.getRol() != null && usuario.getRol().equals(rol)) {
                usuariosPorRol.add(usuario);
            }
        }
        return usuariosPorRol;
    }

    @Override
    public void guardar(Usuario nuevoUsuario) {
        crear(nuevoUsuario);
    }

    private void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}