package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoModificarView extends JInternalFrame implements Idioma {

    private ProductoController productoController;
    private CarritoController carritoController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    private JPanel ModificarCarrito;
    private JComboBox<Carrito> cbxElegirCarrito;
    private JTable tblProductos;
    private JButton btnEliminar;
    private JComboBox<Integer> cbxSelCantidad;
    private JTextField txtIngrProducto;
    private JTextField txtIngrCodProducto;
    private JComboBox<Carrito> cbxIngrProducto;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnCancelar;
    private JLabel lblCantidad;
    private JLabel lblProducto;
    private JLabel lblCodProducto;
    private JLabel lblElegirCarro;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;

    private DefaultTableModel modelo;

    // Constructor principal GUI
    public CarritoModificarView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;
        setContentPane(ModificarCarrito);
        setTitle("Modificar Carrito");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);

        // Configurar tabla
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        // Llenar combo de cantidades
        cbxSelCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxSelCantidad.addItem(i);
        }

        // Evento de selección de fila en tabla
        tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = tblProductos.getSelectedRow();
                if (fila >= 0) {
                    String valor = tblProductos.getValueAt(fila, 3).toString();
                    if (valor.matches("\\d+")) {
                        cbxSelCantidad.setSelectedItem(Integer.parseInt(valor));
                    }
                }
            }
        });

        // Íconos
        btnActualizar.setIcon(escalarIcono("/imagenes/check.png"));
        btnEliminar.setIcon(escalarIcono("/imagenes/cross (1).png"));
        btnAgregar.setIcon(escalarIcono("/imagenes/plus.png"));
        btnCancelar.setIcon(escalarIcono("/imagenes/cross (1).png"));

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
    }

    // Constructor con controladores
    public CarritoModificarView(CarritoController carritoController, ProductoController productoController, MensajeInternacionalizacionHandler mensajeHandler) {
        this(mensajeHandler);
        this.carritoController = carritoController;
        this.productoController = productoController;
    }

    // Método para escalar íconos
    private Icon escalarIcono(String ruta) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        return new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    // Cargar productos en tabla
    public void cargarProductosEnTabla() {
        modelo.setRowCount(0);
        Locale locale = mensajeHandler.getLocale();

        for (ItemCarrito item : carritoController.getCarrito().obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                    item.getCantidad(),
                    FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
            });
        }
    }

    // Actualizar totales (subtotal, iva, total)
    public void actualizarTotales() {
        double subtotal = 0.0;

        for (ItemCarrito item : carritoController.getCarrito().obtenerItems()) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }

        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        Locale locale = mensajeHandler.getLocale();
        txtSubtotal.setText(FormateadorUtils.formatearMoneda(subtotal, locale));
        txtIva.setText(FormateadorUtils.formatearMoneda(iva, locale));
        txtTotal.setText(FormateadorUtils.formatearMoneda(total, locale));
    }

    // Limpiar campos
    public void limpiarCampos() {
        txtIngrProducto.setText("");
        txtIngrCodProducto.setText("");
        cbxSelCantidad.setSelectedIndex(0);
        cbxIngrProducto.setSelectedIndex(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }

    // Mostrar mensaje en ventana
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Idioma
    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        setTitle(bundle.getString("modificar.titulo"));
        lblCantidad.setText(bundle.getString("cantidad"));
        lblProducto.setText(bundle.getString("producto"));
        lblCodProducto.setText(bundle.getString("codigo"));
        lblElegirCarro.setText(bundle.getString("carrito.elegir"));
        lblSubtotal.setText(bundle.getString("subtotal"));
        lblIva.setText(bundle.getString("iva"));
        lblTotal.setText(bundle.getString("total"));

        btnAgregar.setText(bundle.getString("agregar"));
        btnActualizar.setText(bundle.getString("actualizar"));
        btnEliminar.setText(bundle.getString("eliminar"));
        btnCancelar.setText(bundle.getString("cancelar"));
    }

    // Getters necesarios
    public JPanel getModificarCarrito() {
        return ModificarCarrito;
    }

    public JComboBox<Carrito> getCbxElegirCarrito() {
        return cbxElegirCarrito;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JComboBox<Integer> getCbxSelCantidad() {
        return cbxSelCantidad;
    }

    public JTextField getTxtIngrProducto() {
        return txtIngrProducto;
    }

    public JTextField getTxtIngrCodProducto() {
        return txtIngrCodProducto;
    }

    public JComboBox<Carrito> getCbxIngrProducto() {
        return cbxIngrProducto;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}
