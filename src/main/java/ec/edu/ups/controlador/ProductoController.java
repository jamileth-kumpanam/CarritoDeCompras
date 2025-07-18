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

public class ProductoController {

    private ProductoDAO productoDAO;

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoModificarView productoModificarView;
    private ProductoDeleteView productoEliminarView;
    private CarritoAnadirView carritoAnadirView;

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void setProductoAnadirView(ProductoAnadirView view) {
        this.productoAnadirView = view;
        this.productoAnadirView.getBtnCancelar().addActionListener(e -> limpiarCamposAnadir());
        this.productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());
    }

    public void setProductoListaView(ProductoListaView view) {
        this.productoListaView = view;
        this.productoListaView.getBtnBuscar().addActionListener(e -> buscarProductoPorNombre());
        this.productoListaView.getBtnListar().addActionListener(e -> listarProductos());
    }

    public void setProductoModificarView(ProductoModificarView view) {
        this.productoModificarView = view;
        this.productoModificarView.getBtnBuscar().addActionListener(e -> buscarProductoEdicion());
        this.productoModificarView.getBtnActualizar().addActionListener(e -> actualizarProducto());
    }

    public void setProductoEliminarView(ProductoDeleteView view) {
        this.productoEliminarView = view;
        this.productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoEliminar());
        this.productoEliminarView.getBtnDeleteProducto().addActionListener(e -> eliminarProducto());
    }

    public void setCarritoAnadirView(CarritoAnadirView view) {
        this.carritoAnadirView = view;
        this.carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoDesdeCarrito());
    }

    private void limpiarCamposAnadir() {
        if (productoAnadirView != null) {
            productoAnadirView.limpiarCampos();
        }
    }

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

    private void buscarProductoPorNombre() {
        String nombre = productoListaView.getTxtBuscar().getText().trim();
        List<Producto> encontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(encontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

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

    public void agregarProducto(Producto producto) {
        productoDAO.crear(producto);
    }

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

    public List<Producto> buscarPorNombre(String nombre) {
        return productoDAO.buscarPorNombre(nombre);
    }

    public List<Producto> obtenerTodos() {
        return productoDAO.listarTodos();
    }

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
