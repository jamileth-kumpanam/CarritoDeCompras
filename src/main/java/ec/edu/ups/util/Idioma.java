package ec.edu.ups.util;

import java.util.ResourceBundle;
/**
 * Interfaz para componentes que soportan internacionalización.
 */
public interface Idioma {
    /**
     * Actualiza los textos del componente según el ResourceBundle proporcionado.
     * @param bundle ResourceBundle con los textos traducidos.
     */
    void actualizarTextos(ResourceBundle bundle);
}

