package ec.edu.ups.util;

import ec.edu.ups.util.excepciones.CedulaInvalidaException;
import ec.edu.ups.util.excepciones.PasswordInvalidaException;

public class Validaciones {
    public static void validarCedula(String cedula) throws CedulaInvalidaException {
        if (cedula == null || cedula.length() != 10) {
            throw new CedulaInvalidaException("La cédula debe tener 10 dígitos.");
        }
        int total = 0;
        int coeficiente = 2;
        int verificador = Integer.parseInt(cedula.substring(9, 10));
        for (int i = 0; i < 9; i++) {
            int digito = Integer.parseInt(cedula.substring(i, i+1));
            int mult = digito * coeficiente;
            total += (mult > 9) ? mult - 9 : mult;
            coeficiente = (coeficiente == 2) ? 1 : 2;
        }
        int decena = ((total / 10) + 1) * 10;
        int val = decena - total;
        if ((val == 10 ? 0 : val) != verificador) {
            throw new CedulaInvalidaException("Cédula ecuatoriana inválida.");
        }
    }

    public static void validarPassword(String password) throws PasswordInvalidaException {
        if (password == null || password.length() < 6) {
            throw new PasswordInvalidaException("La contraseña debe tener al menos 6 caracteres.");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordInvalidaException("Debe contener al menos una letra mayúscula.");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new PasswordInvalidaException("Debe contener al menos una letra minúscula.");
        }
        if (!password.matches(".*[@_-].*")) {
            throw new PasswordInvalidaException("Debe contener al menos uno de los caracteres: @ _ -");
        }
    }
}