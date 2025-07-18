package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoDeleteView;
import ec.edu.ups.vista.ProductoListaView;
import ec.edu.ups.vista.ProductoModificarView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * Controlador para la gestión de productos: creación, edición, eliminación, búsqueda y listado.
 */
public class ProductoController {
    /**
     * DAO para operaciones de producto.
     */
    private ProductoDAO productoDAO;
    /**
     * Vista para añadir productos.
     */
    private ProductoAnadirView productoAnadirView;
    /**
     * Vista para listar productos.
     */
    private ProductoListaView productoListaView;
    /**
     * Vista para modificar productos.
     */
    private ProductoModificarView productoModificarView;
    /**
     * Vista para eliminar productos.
     */
    private ProductoDeleteView productoEliminarView;
    /**
     * Vista para añadir productos al carrito.
     */
    private CarritoAnadirView carritoAnadirView;
    /**
     * Constructor del controlador de productos.
     * @param productoDAO DAO de producto.
     */
    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
    /**
     * Asigna la vista de añadir producto y configura eventos.
     * @param view Vista de añadir producto.
     */
    public void setProductoAnadirView(ProductoAnadirView view) {
        this.productoAnadirView = view;
        this.productoAnadirView.getBtnCancelar().addActionListener(e -> limpiarCamposAnadir());
        this.productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());
    }
    /**
     * Asigna la vista de lista de productos y configura eventos.
     * @param view Vista de lista de productos.
     */
    public void setProductoListaView(ProductoListaView view) {
        this.productoListaView = view;
        this.productoListaView.getBtnBuscar().addActionListener(e -> buscarProductoPorNombre());
        this.productoListaView.getBtnListar().addActionListener(e -> listarProductos());
    }
    /**
     * Asigna la vista de modificar producto y configura eventos.
     * @param view Vista de modificar producto.
     */
    public void setProductoModificarView(ProductoModificarView view) {
        this.productoModificarView = view;
        this.productoModificarView.getBtnBuscar().addActionListener(e -> buscarProductoEdicion());
        this.productoModificarView.getBtnActualizar().addActionListener(e -> actualizarProducto());
    }
    /**
     * Asigna la vista de eliminar producto y configura eventos.
     * @param view Vista de eliminar producto.
     */
    public void setProductoEliminarView(ProductoDeleteView view) {
        this.productoEliminarView = view;
        this.productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoEliminar());
        this.productoEliminarView.getBtnDeleteProducto().addActionListener(e -> eliminarProducto());
    }
    /**
     * Asigna la vista de añadir producto al carrito y configura eventos.
     * @param view Vista de añadir producto al carrito.
     */
    public void setCarritoAnadirView(CarritoAnadirView view) {
        this.carritoAnadirView = view;
        this.carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoDesdeCarrito());
    }
    /**
     * Limpia los campos de la vista de añadir producto.
     */
    private void limpiarCamposAnadir() {
        if (productoAnadirView != null) {
            productoAnadirView.limpiarCampos();
        }
    }
    /**
     * Guarda un nuevo producto usando los datos de la vista.
     */
    private void guardarProducto() {
        String codigo = productoAnadirView.getTxtCodigo().getText().trim();
        String nombre = productoAnadirView.getTxtNombre().getText().trim();
        String precioStr = productoAnadirView.getTxtPrecio().getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            productoAnadirView.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }
        if (!codigo.matches("\\d+")) {
            productoAnadirView.mostrarMensaje("El código debe ser un número válido.");
            return;
        }
        if (!precioStr.matches("\\d+(\\.\\d+)?")) {
            productoAnadirView.mostrarMensaje("El precio debe ser un número válido.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int codigoInt = Integer.parseInt(codigo);
            Producto producto = new Producto(codigoInt, nombre, precio);

            Producto nuevo = new Producto(codigoInt, nombre, precio);
            productoDAO.crear(nuevo);

            productoAnadirView.mostrarMensaje("Producto guardado correctamente");
            productoAnadirView.limpiarCampos();

            if (productoListaView != null) {
                listarProductos();
            }
        } catch (NumberFormatException e) {
            productoAnadirView.mostrarMensaje("Error al convertir números.");
        }
    }
    /**
     * Busca productos por nombre y los muestra en la vista de lista.
     */
    private void buscarProductoPorNombre() {
        String nombre = productoListaView.getTxtBuscar().getText().trim();
        List<Producto> encontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(encontrados);
    }
    /**
     * Lista todos los productos y los muestra en la vista de lista.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    /**
     * Busca un producto para edición por su código.
     */
    private void buscarProductoEdicion() {
        String codigoStr = productoModificarView.getTxtCodigo().getText().trim();

        if (codigoStr.isEmpty()) {
            productoModificarView.mostrarMensaje("Ingrese un código para buscar.");
            return;
        }

        if (!codigoStr.matches("\\d+")) {
            productoModificarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoModificarView.getTxtNombre().setText(producto.getNombre());
            productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoModificarView.mostrarMensaje("Producto no encontrado.");
            productoModificarView.limpiarCampos();
        }
    }
    /**
     * Actualiza los datos de un producto existente.
     */
    private void actualizarProducto() {
        String codigoStr = productoModificarView.getTxtCodigo().getText().trim();
        String nombre = productoModificarView.getTxtNombre().getText().trim();
        String precioStr = productoModificarView.getTxtPrecio().getText().trim();

        if (codigoStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            productoModificarView.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        if (!codigoStr.matches("\\d+")) {
            productoModificarView.mostrarMensaje("Código inválido.");
            return;
        }

        if (!precioStr.matches("\\d+(\\.\\d+)?")) {
            productoModificarView.mostrarMensaje("Precio inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        double precio = Double.parseDouble(precioStr);

        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            boolean confirmado = productoModificarView.mostrarMensajePregunta("¿Desea actualizar el producto?");
            if (confirmado) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                productoDAO.actualizar(producto);
                productoModificarView.mostrarMensaje("Producto actualizado correctamente.");
            } else {
                productoModificarView.mostrarMensaje("Actualización cancelada.");
            }
        } else {
            productoModificarView.mostrarMensaje("Producto no encontrado.");
        }
    }
    /**
     * Agrega un producto usando el DAO.
     * @param producto Producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        productoDAO.crear(producto);
    }
    /**
     * Busca un producto para eliminar por su código.
     */
    private void buscarProductoEliminar() {
        String codigoStr = productoEliminarView.getTxtCodigo().getText().trim();

        if (codigoStr.isEmpty()) {
            productoEliminarView.mostrarMensaje("Ingrese un código para buscar.");
            return;
        }

        if (!codigoStr.matches("\\d+")) {
            productoEliminarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoEliminarView.mostrarMensaje("Producto encontrado: " + producto.getNombre());
        } else {
            productoEliminarView.mostrarMensaje("Producto no encontrado.");
            productoEliminarView.limpiarCampos();
        }
    }
    /**
     * Elimina un producto por su código.
     */
    private void eliminarProducto() {
        String codigoStr = productoEliminarView.getTxtCodigo().getText().trim();

        if (codigoStr.isEmpty()) {
            productoEliminarView.mostrarMensaje("Ingrese un código para eliminar.");
            return;
        }

        if (!codigoStr.matches("\\d+")) {
            productoEliminarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            boolean confirmado = productoEliminarView.mostrarMensajePregunta("¿Desea eliminar el producto?");
            if (confirmado) {
                productoDAO.eliminar(codigo);
                productoEliminarView.mostrarMensaje("Producto eliminado correctamente.");
                productoEliminarView.limpiarCampos();

                if (productoListaView != null) {
                    listarProductos();
                }
            } else {
                productoEliminarView.mostrarMensaje("Eliminación cancelada.");
            }
        } else {
            productoEliminarView.mostrarMensaje("Producto no encontrado.");
        }
    }
    /**
     * Busca un producto desde la vista de carrito por su código.
     */
    private void buscarProductoDesdeCarrito() {
        String codigoStr = carritoAnadirView.getTxtCodigo().getText().trim();

        if (!codigoStr.matches("\\d+")) {
            carritoAnadirView.mostrarMensaje("El código debe ser un número válido.");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
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
    /**
     * Elimina un producto por su código (como String).
     * @param codigoStr Código del producto.
     * @return true si se eliminó correctamente.
     */
    public boolean eliminarProductoPorCodigo(String codigoStr) {
        if (!codigoStr.matches("\\d+")) {
            return false;
        }

        int codigo = Integer.parseInt(codigoStr);
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoDAO.eliminar(codigo);
            return true;
        }
        return false;
    }
    /**
     * Busca productos por nombre.
     * @param nombre Nombre a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Producto> buscarPorNombre(String nombre) {
        return productoDAO.buscarPorNombre(nombre);
    }
    /**
     * Obtiene todos los productos.
     * @return Lista de productos.
     */
    public List<Producto> obtenerTodos() {
        return productoDAO.listarTodos();
    }
    /**
     * Busca un producto para eliminar y lo muestra en una tabla.
     * @param codigo Código del producto.
     * @param tabla Tabla donde mostrar el producto.
     */
    public void buscarProductoEliminar(int codigo, JTable tabla) {
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpiar la tabla
            modelo.addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getPrecio()});
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }
}
