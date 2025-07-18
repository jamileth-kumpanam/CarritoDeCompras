package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de UsuarioDAO que almacena los usuarios en un archivo de texto plano.
 */
public class UsuarioDAOTexto implements UsuarioDAO {
    /** Ruta del archivo de texto donde se almacenan los usuarios. */
    private String rutaArchivo;
    /**
     * Constructor que recibe la ruta del archivo de texto.
     * @param rutaArchivo Ruta del archivo.
     */
    public UsuarioDAOTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    /**
     * Autentica un usuario verificando nombre de usuario y contraseña.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o null si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }
    /**
     * Crea un nuevo usuario y lo guarda en el archivo de texto.
     * @param usuario Usuario a crear.
     */
    @Override
    public void crear(Usuario usuario) {
        guardar(usuario);
    }
    /**
     * Busca un usuario por su nombre de usuario en el archivo de texto.
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null.
     */
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
    /**
     * Elimina un usuario del archivo de texto por su nombre de usuario.
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarLista(usuarios);
    }
    /**
     * Actualiza la información de un usuario existente en el archivo de texto.
     * @param usuario Usuario con datos actualizados.
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
        guardarLista(usuarios);
    }
    /**
     * Devuelve la lista de todos los usuarios almacenados en el archivo de texto.
     * @return Lista de usuarios.
     */
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
    /**
     * Lista los usuarios que tienen un rol específico.
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol dado.
     */
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
    /**
     * Guarda un usuario en el archivo de texto (agrega uno nuevo).
     * @param usuario Usuario a guardar.
     */
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
    /**
     * Guarda la lista completa de usuarios en el archivo de texto.
     * @param usuarios Lista de usuarios a guardar.
     */
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