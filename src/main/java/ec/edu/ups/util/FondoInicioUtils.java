package ec.edu.ups.util;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
/**
 * Utilidad para establecer una imagen de fondo en un JFrame.
 */
public class FondoInicioUtils {
    /**
     * Coloca una imagen de fondo en el JFrame y la ajusta al tamaño de la ventana.
     * @param frame Ventana donde se colocará el fondo.
     * @param rutaImagen Ruta de la imagen de fondo (relativa al classpath).
     */
    public static void ponerFondo(JFrame frame, String rutaImagen) {
        JLabel fondo = new JLabel(new ImageIcon(frame.getClass().getResource(rutaImagen)));
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.getContentPane().add(fondo);
        frame.getContentPane().setComponentZOrder(fondo, frame.getContentPane().getComponentCount() - 1);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                fondo.setSize(frame.getWidth(), frame.getHeight());
            }
        });
    }
}
