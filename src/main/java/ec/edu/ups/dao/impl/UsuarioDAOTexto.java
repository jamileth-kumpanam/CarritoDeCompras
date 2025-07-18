package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {
    private String rutaArchivo;

    public UsuarioDAOTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        guardar(usuario);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarLista(usuarios);
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarLista(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) return usuarios;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length >= 13) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(campos[0]);
                    usuario.setContrasenia(campos[1]);
                    usuario.setRol(Rol.valueOf(campos[2]));
                    usuario.setPregunta1(campos[3]);
                    usuario.setRespuesta1(campos[4]);
                    usuario.setPregunta2(campos[5]);
                    usuario.setRespuesta2(campos[6]);
                    usuario.setPregunta3(campos[7]);
                    usuario.setRespuesta3(campos[8]);
                    usuario.setNombre(campos[9]);
                    usuario.setFechaNacimiento(campos[10]);
                    usuario.setTelefono(campos[11]);
                    usuario.setCorreo(campos[12]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuarios = listarTodos();
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }

    @Override
    public void guardar(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(usuario.getUsername() + ";" +
                    usuario.getContrasenia() + ";" +
                    usuario.getRol() + ";" +
                    usuario.getPregunta1() + ";" +
                    usuario.getRespuesta1() + ";" +
                    usuario.getPregunta2() + ";" +
                    usuario.getRespuesta2() + ";" +
                    usuario.getPregunta3() + ";" +
                    usuario.getRespuesta3() + ";" +
                    usuario.getNombre() + ";" +
                    usuario.getFechaNacimiento() + ";" +
                    usuario.getTelefono() + ";" +
                    usuario.getCorreo());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarLista(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getUsername() + ";" +
                        usuario.getContrasenia() + ";" +
                        usuario.getRol() + ";" +
                        usuario.getPregunta1() + ";" +
                        usuario.getRespuesta1() + ";" +
                        usuario.getPregunta2() + ";" +
                        usuario.getRespuesta2() + ";" +
                        usuario.getPregunta3() + ";" +
                        usuario.getRespuesta3() + ";" +
                        usuario.getNombre() + ";" +
                        usuario.getFechaNacimiento() + ";" +
                        usuario.getTelefono() + ";" +
                        usuario.getCorreo());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}