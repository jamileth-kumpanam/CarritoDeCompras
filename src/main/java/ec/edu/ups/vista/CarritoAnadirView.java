package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.FormateadorUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoAnadirView extends JInternalFrame implements Idioma {
    public CarritoAnadirView(){}

    // Componentes Swing
    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox<String> cbxCantidad;
    private JPanel panelPrincipal;
    private JButton cancelarButton;

    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;

    // Controladores y handler
    private CarritoController carritoController;
    private ProductoController productoController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    // Constructor principal
    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Carrito de Compras", true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        // Configurar tabla con columnas
        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatosComboCantidad();
        actualizarTextos(mensajeHandler.getBundle());

        // Cargar iconos con escala y asignar a botones
        btnBuscar.setIcon(cargarIcono("/imagenes/search.png", 20, 20));
        btnAnadir.setIcon(cargarIcono("/imagenes/plus.png", 20, 20));
        btnGuardar.setIcon(cargarIcono("/imagenes/shield-check.png", 20, 20));
        btnLimpiar.setIcon(cargarIcono("/imagenes/broom.png", 20, 20));
        cancelarButton.setIcon(cargarIcono("/imagenes/cross (1).png", 20, 20));

        // Listeners
        cancelarButton.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    // Constructor con controladores
    public CarritoAnadirView(CarritoController carritoController, ProductoController productoController, MensajeInternacionalizacionHandler mensajeHandler) {
        this(mensajeHandler);
        this.carritoController = carritoController;
        this.productoController = productoController;
    }

    // Método para cargar datos al combo de cantidad
    private void cargarDatosComboCantidad() {
        if (cbxCantidad != null) {
            cbxCantidad.removeAllItems();
            for (int i = 1; i <= 20; i++) {
                cbxCantidad.addItem(String.valueOf(i));
            }
        }
    }

    // Método para limpiar campos y tabla
    public void limpiarCampos() {
        if (txtCodigo != null) txtCodigo.setText("");
        if (txtNombre != null) txtNombre.setText("");
        if (txtPrecio != null) txtPrecio.setText("");
        if (txtSubtotal != null) txtSubtotal.setText("");
        if (txtIva != null) txtIva.setText("");
        if (txtTotal != null) txtTotal.setText("");
        if (cbxCantidad != null) cbxCantidad.setSelectedIndex(0);

        if (tblProductos != null) {
            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.setRowCount(0); // limpiar filas
        }
    }

    // Método para mostrar mensajes al usuario
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Actualiza textos de etiquetas y botones para internacionalización
    public void actualizarTextos(ResourceBundle bundle) {
        if (lblCodigo != null) lblCodigo.setText(mensajeHandler.get("login.Codigo"));
        if (lblNombre != null) lblNombre.setText(mensajeHandler.get("login.Nombre"));
        if (lblPrecio != null) lblPrecio.setText(mensajeHandler.get("login.Precio"));
        if (lblCantidad != null) lblCantidad.setText(mensajeHandler.get("login.Cantidad"));

        if (btnBuscar != null) btnBuscar.setText(mensajeHandler.get("login.Buscar"));
        if (btnAnadir != null) btnAnadir.setText(mensajeHandler.get("login.anadir"));
        if (btnGuardar != null) btnGuardar.setText(mensajeHandler.get("login.guardar"));
        if (btnLimpiar != null) btnLimpiar.setText(mensajeHandler.get("login.limpiar"));
        if (cancelarButton != null) cancelarButton.setText(mensajeHandler.get("login.cancelar"));
    }

    // Actualiza los totales con formato de moneda según locale
    public void actualizarTotalesFormateados(double subtotal, double iva, double total) {
        Locale locale = mensajeHandler.getLocale();
        if (txtSubtotal != null) txtSubtotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        if (txtIva != null) txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        if (txtTotal != null) txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    // Utilidad para cargar y escalar iconos
    private ImageIcon cargarIcono(String path, int ancho, int alto) {
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(path));
            Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imgEscalada);
        } catch (Exception e) {
            System.err.println("No se pudo cargar el icono: " + path);
            return null;
        }
    }

    // Getters y setters

    public JButton getBtnBuscar() { return btnBuscar; }
    public void setBtnBuscar(JButton btnBuscar) { this.btnBuscar = btnBuscar; }

    public JTextField getTxtCodigo() { return txtCodigo; }
    public void setTxtCodigo(JTextField txtCodigo) { this.txtCodigo = txtCodigo; }

    public JTextField getTxtNombre() { return txtNombre; }
    public void setTxtNombre(JTextField txtNombre) { this.txtNombre = txtNombre; }

    public JTextField getTxtPrecio() { return txtPrecio; }
    public void setTxtPrecio(JTextField txtPrecio) { this.txtPrecio = txtPrecio; }

    public JButton getBtnAnadir() { return btnAnadir; }
    public void setBtnAnadir(JButton btnAnadir) { this.btnAnadir = btnAnadir; }

    public JTable getTblProductos() { return tblProductos; }
    public void setTblProductos(JTable tblProductos) { this.tblProductos = tblProductos; }

    public JTextField getTxtSubtotal() { return txtSubtotal; }
    public void setTxtSubtotal(JTextField txtSubtotal) { this.txtSubtotal = txtSubtotal; }

    public JTextField getTxtIva() { return txtIva; }
    public void setTxtIva(JTextField txtIva) { this.txtIva = txtIva; }

    public JTextField getTxtTotal() { return txtTotal; }
    public void setTxtTotal(JTextField txtTotal) { this.txtTotal = txtTotal; }

    public JButton getBtnGuardar() { return btnGuardar; }
    public void setBtnGuardar(JButton btnGuardar) { this.btnGuardar = btnGuardar; }

    public JButton getBtnLimpiar() { return btnLimpiar; }
    public void setBtnLimpiar(JButton btnLimpiar) { this.btnLimpiar = btnLimpiar; }

    public JComboBox<String> getCbxCantidad() { return cbxCantidad; }
    public void setCbxCantidad(JComboBox<String> cbxCantidad) { this.cbxCantidad = cbxCantidad; }

    public JPanel getPanelPrincipal() { return panelPrincipal; }
    public void setPanelPrincipal(JPanel panelPrincipal) { this.panelPrincipal = panelPrincipal; }

    public JButton getCancelarButton() { return cancelarButton; }
    public void setCancelarButton(JButton cancelarButton) { this.cancelarButton = cancelarButton; }

    public MensajeInternacionalizacionHandler getMensajeHandler() { return mensajeHandler; }
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) { this.mensajeHandler = mensajeHandler; }

    public CarritoController getCarritoController() { return carritoController; }
    public void setCarritoController(CarritoController carritoController) { this.carritoController = carritoController; }

    public ProductoController getProductoController() { return productoController; }
    public void setProductoController(ProductoController productoController) { this.productoController = productoController; }

    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }
}
