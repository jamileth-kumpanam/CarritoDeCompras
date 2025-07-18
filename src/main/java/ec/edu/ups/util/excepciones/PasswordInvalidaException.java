package ec.edu.ups.util.excepciones;

public class PasswordInvalidaException extends Exception {
    public PasswordInvalidaException(String mensaje) {
        super(mensaje);
    }
}