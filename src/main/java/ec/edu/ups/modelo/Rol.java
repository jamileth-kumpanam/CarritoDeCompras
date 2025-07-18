package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Enum que representa los roles posibles de un usuario en el sistema.
 */
public enum Rol implements Serializable {
    /** Rol de administrador con privilegios elevados. */
    ADMINISTRADOR,
    /** Rol de usuario estándar. */
    USUARIO
}