package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ResourceBundle;
/**
 * Ventana interna para mostrar la lista de productos en el carrito.
 * Permite visualizar los productos agregados y cerrar la vista.
 */
public class CarritoListaView extends JInternalFrame implements Idioma {

    private JPanel panelPrincipal;
    private JTable tblCarrito;
    private JButton btnCerrar;
    private JLabel lblTitulo;

    private CarritoController carritoController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoListaView(CarritoController carritoController, MensajeInternacionalizacionHandler mensajeHandler) {
        super("Lista del Carrito", true, true, false, true);
        this.carritoController = carritoController;
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblCarrito.setModel(modelo);

        actualizarTextos(mensajeHandler.getBundle());

        btnCerrar.addActionListener(e -> dispose());
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        lblTitulo.setText(mensajeHandler.get("carrito.lista.titulo"));
        btnCerrar.setText(mensajeHandler.get("login.cancelar"));
    }

    // Getters y Setters

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTblCarrito() {
        return tblCarrito;
    }

    public void setTblCarrito(JTable tblCarrito) {
        this.tblCarrito = tblCarrito;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setBtnCerrar(JButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public CarritoController getCarritoController() {
        return carritoController;
    }

    public void setCarritoController(CarritoController carritoController) {
        this.carritoController = carritoController;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
}