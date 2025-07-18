// src/main/java/ec/edu/ups/vista/CarritoAnadirView.java
package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoAnadirView extends JInternalFrame implements Idioma {

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

    private CarritoController carritoController;
    private ProductoController productoController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoAnadirView(CarritoController carritoController, ProductoController productoController, MensajeInternacionalizacionHandler mensajeHandler) {
        super("Carrito de Compras", true, true, false, true);
        this.carritoController = carritoController;
        this.productoController = productoController;
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatosComboCantidad();
        actualizarTextos(mensajeHandler.getBundle());

        cancelarButton.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        configurarEventos();
    }

    private void configurarEventos() {
        if (btnAnadir != null) btnAnadir.addActionListener(e -> {
            Producto productoSeleccionado = obtenerProductoSeleccionado();
            int cantidad = obtenerCantidad();
            if (productoSeleccionado != null && cantidad > 0) {
                carritoController.agregarProductoAlCarrito();
                actualizarVistaCarrito();
            }
        });
        if (btnBuscar != null) btnBuscar.addActionListener(e -> carritoController.buscarProducto());
        if (btnGuardar != null) btnGuardar.addActionListener(e -> carritoController.guardarCarrito());
    }

    private void cargarDatosComboCantidad() {
        if (cbxCantidad != null) {
            cbxCantidad.removeAllItems();
            for (int i = 1; i <= 20; i++) {
                cbxCantidad.addItem(String.valueOf(i));
            }
        }
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        cbxCantidad.setSelectedIndex(0);

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        lblCodigo.setText(mensajeHandler.get("login.Codigo"));
        lblNombre.setText(mensajeHandler.get("login.Nombre"));
        lblPrecio.setText(mensajeHandler.get("login.Precio"));
        lblCantidad.setText(mensajeHandler.get("login.Cantidad"));

        btnBuscar.setText(mensajeHandler.get("login.Buscar"));
        btnAnadir.setText(mensajeHandler.get("login.anadir"));
        btnGuardar.setText(mensajeHandler.get("login.guardar"));
        btnLimpiar.setText(mensajeHandler.get("login.limpiar"));
        cancelarButton.setText(mensajeHandler.get("login.cancelar"));
    }

    public void actualizarTotalesFormateados(double subtotal, double iva, double total) {
        Locale locale = mensajeHandler.getLocale();
        txtSubtotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    public void actualizarVistaCarrito() {
        List<ItemCarrito> items = carritoController.obtenerItemsCarrito();

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            Object[] fila = {
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), mensajeHandler.getLocale()),
                    item.getCantidad(),
                    FormateadorUtils.formatearMoneda(item.getSubtotal(), mensajeHandler.getLocale())
            };
            modelo.addRow(fila);
        }
    }

    private Producto obtenerProductoSeleccionado() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            return new Producto(codigo, nombre, precio);
        } catch (Exception e) {
            mostrarMensaje("Datos de producto inválidos.");
            return null;
        }
    }

    private int obtenerCantidad() {
        try {
            return Integer.parseInt((String) cbxCantidad.getSelectedItem());
        } catch (Exception e) {
            return 0;
        }
    }

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

    public CarritoController getCarritoController() { return carritoController; }
    public ProductoController getProductoController() { return productoController; }
    public void setProductoController(ProductoController productoController) { this.productoController = productoController; }
    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {}
}