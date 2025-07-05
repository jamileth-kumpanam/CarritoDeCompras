package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoDeleteView;
import ec.edu.ups.vista.ProductoListaView;
import ec.edu.ups.vista.ProductoModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductoController {

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    private ProductoDAO productoDAO;

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoModificarView productoModificarView;
    private ProductoDeleteView productoEliminarView;
    private CarritoAnadirView carritoAnadirView;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoModificarView productoModificarView,
                              ProductoDeleteView productoEliminarView,
                              CarritoAnadirView carritoAnadirView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;

        configurarEventos();
    }

    private void configurarEventos() {
        productoAnadirView.getBtnLimpiar().addActionListener(e -> guardarProducto());

        productoListaView.getBtnBuscar().addActionListener(e -> buscarProductoPorNombre());

        productoListaView.getBtnListar().addActionListener(e -> listarProductos());

        productoModificarView.getBtnActualizar().addActionListener(e -> buscarProductoEdicion());

        productoModificarView.getBtnActualizar().addActionListener(e -> actualizarProducto());

        productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoEliminar());

        productoEliminarView.getBtnDeleteProducto().addActionListener(e -> eliminarProducto());

        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoDesdeCarrito());
    }

    private void guardarProducto() {
        String txtCod = productoAnadirView.getTxtCodigo().getText().trim();
        String nombre = productoAnadirView.getTxtNombre().getText().trim();
        String txtPrecio = productoAnadirView.getTxtPrecio().getText().trim();

        if (txtCod.isEmpty() || nombre.isEmpty() || txtPrecio.isEmpty()) {
            productoAnadirView.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        if (!txtCod.matches("\\d+")) {
            productoAnadirView.mostrarMensaje("El código debe ser un número válido.");
            return;
        }

        if (!txtPrecio.matches("\\d+(\\.\\d+)?")) {
            productoAnadirView.mostrarMensaje("El precio debe ser un número válido.");
            return;
        }

        int codigo = Integer.parseInt(txtCod);
        double precio = Double.parseDouble(txtPrecio);

        Producto nuevo = new Producto(codigo, nombre, precio);
        productoDAO.crear(nuevo);
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
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
        String txtCod = productoModificarView.getTxtCodigo().getText().trim();

        if (txtCod.isEmpty()) {
            productoModificarView.mostrarMensaje("Ingresa un código para buscar.");
            return;
        }

        if (!txtCod.matches("\\d+")) {
            productoModificarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(txtCod);
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
        String txtCod = productoModificarView.getTxtCodigo().getText().trim();
        String nombre = productoModificarView.getTxtNombre().getText().trim();
        String txtPrecio = productoModificarView.getTxtPrecio().getText().trim();

        if (txtCod.isEmpty() || nombre.isEmpty() || txtPrecio.isEmpty()) {
            productoModificarView.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        if (!txtCod.matches("\\d+")) {
            productoModificarView.mostrarMensaje("Código inválido.");
            return;
        }

        if (!txtPrecio.matches("\\d+(\\.\\d+)?")) {
            productoModificarView.mostrarMensaje("Precio inválido.");
            return;
        }

        int codigo = Integer.parseInt(txtCod);
        double precio = Double.parseDouble(txtPrecio);
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

    private void buscarProductoEliminar() {
        String txtCod = productoEliminarView.getTxtCodigo().getText().trim();

        if (!txtCod.matches("\\d+")) {
            productoEliminarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(txtCod);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoEliminarView.mostrarMensaje("Producto encontrado: " + producto.getNombre());
        } else {
            productoEliminarView.mostrarMensaje("Producto no encontrado.");
            productoEliminarView.limpiarCampos();
        }
    }

    private void eliminarProducto() {
        String txtCod = productoEliminarView.getTxtCodigo().getText().trim();

        if (!txtCod.matches("\\d+")) {
            productoEliminarView.mostrarMensaje("Código inválido.");
            return;
        }

        int codigo = Integer.parseInt(txtCod);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            boolean confirmado = productoEliminarView.mostrarMensajePregunta("¿Desea eliminar el producto?");
            if (confirmado) {
                productoDAO.eliminar(codigo);
                productoEliminarView.mostrarMensaje("Producto eliminado correctamente.");
                productoEliminarView.limpiarCampos();
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

    public void setProductoAnadirView(ProductoAnadirView view) { this.productoAnadirView = view; }
    public void setProductoListaView(ProductoListaView view) { this.productoListaView = view; }
    public void setProductoModificarView(ProductoModificarView view) { this.productoModificarView = view; }
    public void setProductoEliminarView(ProductoDeleteView view) { this.productoEliminarView = view; }
    public void setCarritoAnadirView(CarritoAnadirView view) { this.carritoAnadirView = view; }
}
