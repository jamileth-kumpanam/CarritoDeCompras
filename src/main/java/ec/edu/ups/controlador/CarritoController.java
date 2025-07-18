package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.servicio.CarritoServiceImpl;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoModificarView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * Controlador para la gestión del carrito de compras: agregar, modificar, eliminar productos y guardar el carrito.
 */
public class CarritoController {
    /**
     * DAO para operaciones de carrito.
     */
    private final CarritoDAO carritoDAO;
    /**
     * DAO para operaciones de producto.
     */
    private final ProductoDAO productoDAO;
    /**
     * Vista para añadir productos al carrito.
     */
    private CarritoAnadirView carritoAnadirView;
    /**
     * Vista para modificar productos en el carrito.
     */
    private CarritoModificarView carritoModificarView;
    /**
     * Manejador de mensajes internacionalizados.
     */
    private final MensajeInternacionalizacionHandler mensajeHandler;
    /**
     * Servicio para la lógica del carrito.
     */
    private final CarritoServiceImpl carritoService;
    /**
     * Constructor del controlador de carrito.
     * @param carritoDAO DAO de carrito.
     * @param productoDAO DAO de producto.
     * @param carritoModificarView Vista de modificación de carrito.
     * @param mensajeHandler Manejador de mensajes.
     */
    public CarritoController(CarritoDAO carritoDAO, ProductoDAO productoDAO, CarritoModificarView carritoModificarView, MensajeInternacionalizacionHandler mensajeHandler) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoModificarView = carritoModificarView;
        this.mensajeHandler = mensajeHandler;
        this.carritoService = new CarritoServiceImpl();
    }

    /**
     * Asigna la vista de añadir producto al carrito.
     * @param carritoAnadirView Vista de añadir producto.
     */
    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {
        this.carritoAnadirView = carritoAnadirView;
    }
    /**
     * Asigna la vista de modificar productos en el carrito.
     * @param carritoModificarView Vista de modificar productos.
     */
    public void setCarritoModificarView(CarritoModificarView carritoModificarView) {
        this.carritoModificarView = carritoModificarView;
    }
    /**
     * Busca un producto por código y muestra sus datos en la vista correspondiente.
     */
    public void buscarProducto() {
        String codigoStr;
        JTextField txtCodigo;
        JTextField txtNombre;
        JTextField txtPrecio;

        if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
            txtCodigo = carritoAnadirView.getTxtCodigo();
            txtNombre = carritoAnadirView.getTxtNombre();
            txtPrecio = carritoAnadirView.getTxtPrecio();
        } else if (carritoModificarView != null && carritoModificarView.isVisible()) {
            txtCodigo = carritoModificarView.getCodigoTextField();
            txtNombre = carritoModificarView.getNombreTextField();
            txtPrecio = carritoModificarView.getPrecioTextField();
        } else {
            return;
        }

        codigoStr = txtCodigo.getText();
        if (codigoStr.isEmpty()) {
            mostrarMensaje("Ingrese un código de producto.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                txtNombre.setText(producto.getNombre());
                txtPrecio.setText(String.valueOf(producto.getPrecio()));
            } else {
                mostrarMensaje("Producto no encontrado.");
                txtNombre.setText("");
                txtPrecio.setText("");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Código inválido.");
        }
    }
    /**
     * Agrega un producto al carrito usando los datos de la vista.
     */
    public void agregarProductoAlCarrito() {
        if (carritoAnadirView == null) return;

        String codigoStr = carritoAnadirView.getTxtCodigo().getText();
        String cantidadStr = (String) carritoAnadirView.getCbxCantidad().getSelectedItem();

        if (codigoStr.isEmpty() || cantidadStr == null) {
            mostrarMensaje("Ingrese código y cantidad.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            int cantidad = Integer.parseInt(cantidadStr);

            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto == null) {
                mostrarMensaje("Producto no encontrado.");
                return;
            }

            carritoService.agregarProducto(producto, cantidad);
            actualizarTablaProductos();
            actualizarTotales();
        } catch (NumberFormatException e) {
            mostrarMensaje("Datos inválidos.");
        }
    }
    /**
     * Obtiene la lista de ítems del carrito.
     * @return Lista de ítems.
     */
    public List<ItemCarrito> obtenerItemsCarrito() {
        return carritoService.obtenerItems();
    }
    /**
     * Guarda el carrito actual y lo vacía.
     */
    public void guardarCarrito() {
        List<ItemCarrito> items = carritoService.obtenerItems();
        if (items.isEmpty()) {
            mostrarMensaje("El carrito está vacío.");
            return;
        }
        Carrito carrito = new Carrito();
        carrito.setItems(items);
        carritoDAO.guardar(carrito);
        mostrarMensaje("Carrito guardado correctamente.");
        carritoService.vaciarCarrito();
        actualizarTablaProductos();
        actualizarTotales();
    }
    /**
     * Actualiza la tabla de productos en la vista.
     */
    private void actualizarTablaProductos() {
        List<ItemCarrito> items = carritoService.obtenerItems();
        DefaultTableModel modelo;
        if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
            modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        } else if (carritoModificarView != null && carritoModificarView.isVisible()) {
            modelo = (DefaultTableModel) carritoModificarView.getProductosTable().getModel();
        } else {
            return;
        }
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            Object[] fila = {
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad()
            };
            modelo.addRow(fila);
        }
    }
    /**
     * Actualiza los totales (subtotal, IVA, total) en la vista.
     */
    private void actualizarTotales() {
        double subtotal = carritoService.calcularTotal();
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
            carritoAnadirView.actualizarTotalesFormateados(subtotal, iva, total);
        } else if (carritoModificarView != null && carritoModificarView.isVisible()) {
            carritoModificarView.getSubtotalTextField().setText(String.format("%.2f", subtotal));
            carritoModificarView.getIvaTextField().setText(String.format("%.2f", iva));
            carritoModificarView.getTotalTextField().setText(String.format("%.2f", total));
        }
    }
    /**
     * Muestra un mensaje en la vista correspondiente.
     * @param mensaje Mensaje a mostrar.
     */
    private void mostrarMensaje(String mensaje) {
        if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
            carritoAnadirView.mostrarMensaje(mensaje);
        } else if (carritoModificarView != null && carritoModificarView.isVisible()) {
            JOptionPane.showMessageDialog(carritoModificarView, mensaje);
        }
    }
    /**
     * Modifica la cantidad de un producto en el carrito.
     */
    public void modificarProductoEnCarrito() {
        if (carritoModificarView == null) return;

        int fila = carritoModificarView.getProductosTable().getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("Seleccione un producto para modificar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getProductosTable().getModel();
        int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        int nuevaCantidad = Integer.parseInt((String) carritoModificarView.getCantidadComboBox().getSelectedItem());

        carritoService.eliminarProducto(codigo);
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            carritoService.agregarProducto(producto, nuevaCantidad);
        }
        actualizarTablaProductos();
        actualizarTotales();
    }
    /**
     * Elimina un producto del carrito.
     */
    public void eliminarProductoDelCarrito() {
        if (carritoModificarView == null) return;

        int fila = carritoModificarView.getProductosTable().getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("Seleccione un producto para eliminar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getProductosTable().getModel();
        int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        carritoService.eliminarProducto(codigo);
        actualizarTablaProductos();
        actualizarTotales();
    }
}