package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Manejador para la internacionalización de mensajes en la aplicación.
 * Permite obtener textos traducidos según el locale seleccionado.
 */
public class MensajeInternacionalizacionHandler {
    /** ResourceBundle con los textos traducidos. */
    private ResourceBundle bundle;
    /** Locale actual de la aplicación. */
    private Locale locale;
    /**
     * Constructor que inicializa el handler con un lenguaje y país.
     * @param lenguaje Código de idioma (ej: "es").
     * @param pais Código de país (ej: "EC").
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("messages", locale);
    }
    /**
     * Constructor que inicializa el handler con un locale específico.
     * @param locale Locale a utilizar.
     */
    public MensajeInternacionalizacionHandler(Locale locale) {
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("messages", locale);
    }
    /**
     * Obtiene el texto traducido para la clave dada.
     * @param key Clave del mensaje.
     * @return Texto traducido o "!key!" si no se encuentra.
     */
    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            System.err.println("Clave no encontrada: " + key);
            return "!" + key + "!";
        }
    }
    /**
     * Cambia el lenguaje y país del handler.
     * @param lenguaje Código de idioma.
     * @param pais Código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("messages", locale);
    }
    /** @return Locale actual. */
    public Locale getLocale() {
        return this.locale;
    }
    /** @return Locale actual. */
    public ResourceBundle getBundle() {
        return bundle;
    }

}