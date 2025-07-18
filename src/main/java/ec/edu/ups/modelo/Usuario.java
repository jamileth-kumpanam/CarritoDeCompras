package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Representa un usuario del sistema, con credenciales, datos personales y rol.
 */
public class Usuario implements Serializable {
    /** Código único del usuario. */
    private int codigo;
    /** Nombre de usuario (login). */
    private String username;
    /** Contraseña del usuario. */
    private String contrasenia;
    /** Rol del usuario (ADMINISTRADOR o USUARIO). */
    private Rol rol;
    /** Preguntas y respuestas de seguridad. */
    private String pregunta1, respuesta1, pregunta2, respuesta2, pregunta3, respuesta3;
    /** Datos personales. */
    private String nombre, fechaNacimiento, telefono, correo;
    /** Constructor vacío. */
    public Usuario() {}
    /**
     * Constructor con código, nombre, username y contraseña.
     * @param codigo Código del usuario.
     * @param nombre Nombre real.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     */
    public Usuario(int codigo, String nombre, String username, String contrasenia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.username = username;
        this.contrasenia = contrasenia;
    }
    /**
     * Constructor con username, contraseña y rol.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @param rol Rol del usuario.
     */
    public Usuario(String username, String contrasenia, Rol rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Crea un objeto Usuario a partir de una línea de texto.
     * @param linea Línea con los datos del usuario.
     * @return Usuario creado.
     */
    public static Usuario desdeString(String linea) {
        String[] partes = linea.split(";");
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        String username = partes[2];
        String contrasenia = partes[3];
        return new Usuario(id, nombre, username, contrasenia);
    }
    /** @return Código único del usuario. */
    public int getCodigo() {
        return codigo;
    }
    /** @return Representación en texto del usuario. */
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
    /** @param usuarioNombre Nombre de usuario a asignar. */
    public void setUsuario(String usuarioNombre) {
        this.username = usuarioNombre;
    }

}
