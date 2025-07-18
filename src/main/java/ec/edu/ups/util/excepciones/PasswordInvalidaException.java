package ec.edu.ups.util.excepciones;
/**
 * Excepción personalizada que indica que una contraseña es inválida.
 */
public class PasswordInvalidaException extends Exception {
    /**
     * Constructor que recibe el mensaje de error.
     * @param mensaje Mensaje descriptivo del error.
     */
    public PasswordInvalidaException(String mensaje) {
        super(mensaje);
    }
}