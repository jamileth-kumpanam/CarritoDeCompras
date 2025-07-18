package ec.edu.ups.util.excepciones;
/**
 * Excepción personalizada que indica que una cédula es inválida.
 */
public class CedulaInvalidaException extends Exception {
    /**
 * Constructor que recibe el mensaje de error.
 * @param mensaje Mensaje descriptivo del error.
 */
    public CedulaInvalidaException(String mensaje) {
        super(mensaje);
    }
}