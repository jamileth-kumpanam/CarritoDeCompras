package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Utilidad para formatear monedas y fechas según la configuración regional.
 */
public class FormateadorUtils {
    /**
     * Formatea una cantidad como moneda según el locale especificado.
     * @param cantidad Valor numérico a formatear.
     * @param locale Configuración regional.
     * @return Cadena con el valor formateado como moneda.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }
    /**
     * Formatea una fecha según el locale especificado.
     * @param fecha Fecha a formatear.
     * @param locale Configuración regional.
     * @return Cadena con la fecha formateada.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }
}