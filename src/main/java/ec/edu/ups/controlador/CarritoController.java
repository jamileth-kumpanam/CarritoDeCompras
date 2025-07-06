package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.vista.CarritoAnadirView;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;

    private Carrito carritoActual;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;

        this.carritoActual = new Carrito();
        configurarEventos();
    }

    private void configurarEventos() {
        carritoAnadirView.getBtnAnadir().addActionListener(e -> anadirProducto());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getBtnLimpiar().addActionListener(e -> carritoAnadirView.limpiarCampos());
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProducto());
    }

    private void buscarProducto() {
        String codigoStr = carritoAnadirView.getTxtCodigo().getText().trim();
        if (!codigoStr.matches("\\d+")) {
            carritoAnadirView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.mostrarMensaje("Producto no encontrado.");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
    }

    private void anadirProducto() {
        String codigoStr = carritoAnadirView.getTxtCodigo().getText().trim();
        if (!codigoStr.matches("\\d+")) {
            carritoAnadirView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.mostrarMensaje("Producto no encontrado.");
            return;
        }

        String cantidadStr = carritoAnadirView.getCbxCantidad().getSelectedItem().toString();
        if (!cantidadStr.matches("\\d+")) {
            carritoAnadirView.mostrarMensaje("Cantidad inválida.");
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);
        carritoActual.agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotales();
        carritoAnadirView.limpiarCampos();
    }

    private void cargarProductos() {
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);

        List<ItemCarrito> items = carritoActual.obtenerItems();
        Locale locale = carritoAnadirView.getMensajeInternacionalizacion().getLocale();

        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
            });
        }
    }

    private void mostrarTotales() {
        Locale locale = carritoAnadirView.getMensajeInternacionalizacion().getLocale();

        String subtotal = FormateadorUtils.formatearMoneda(carritoActual.calcularSubtotal(), locale);
        String iva = FormateadorUtils.formatearMoneda(carritoActual.calcularIVA(), locale);
        String total = FormateadorUtils.formatearMoneda(carritoActual.calcularTotal(), locale);

        carritoAnadirView.getTxtSubtotal().setText(subtotal);
        carritoAnadirView.getTxtIva().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }

    private void guardarCarrito() {
        carritoDAO.crear(carritoActual);
        carritoAnadirView.mostrarMensaje("Carrito guardado correctamente.");

        carritoActual = new Carrito(); // Reiniciar carrito
        cargarProductos();
        mostrarTotales();
        carritoAnadirView.limpiarCampos();
    }

    public Carrito getCarrito() {
        return carritoActual;
    }

    public void setCarrito(Carrito carrito) {
        this.carritoActual = carrito;
    }
}
