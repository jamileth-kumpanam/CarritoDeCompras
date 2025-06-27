package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private ProductoAniadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoEliminarView productoDeleteView;
    private ActualizarProductos actualizar;
    private CarritoAnadirView carritoAnadirView;

    private ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAniadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoEliminarView productoDeleteView,
                              ActualizarProductos actualizar,
                              CarritoAnadirView carritoAnadirView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDeleteView = productoDeleteView;
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventos();
    }

    private void configurarEventos() {

        // Guardar producto
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        // Buscar para actualizar
        actualizar.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoAc();
            }
        });

        // Modificar producto
        actualizar.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        productoDeleteView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        // Buscar en lista
        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        // Listar todos
        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });

        // Buscar producto en carrito
        carritoAnadirView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoEnCarrito();
            }
        });
    }

    private void guardarProducto() {
        try {
            int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
            String nombre = productoAnadirView.getTxtNombre().getText();
            double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

            productoDAO.crear(new Producto(codigo, nombre, precio));
            productoAnadirView.mostrarMensaje("Producto guardado correctamente");
            productoAnadirView.limpiarCampos();
        } catch (Exception e) {
            productoAnadirView.mostrarMensaje("Error al guardar producto: " + e.getMessage());
        }
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productos = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productos);
    }

    private void eliminarProducto() {
        try {
            int codigo = Integer.parseInt(productoDeleteView.getTxtCodigoEliminar().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                productoDAO.eliminar(codigo);
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
        }
    }

    private void buscarPorCodigoAc() {
        try {
            int codigo = Integer.parseInt(actualizar.getTextField1().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                actualizar.getTextNombreShow().setText(producto.getNombre());
                actualizar.getTextPrecioShow().setText(String.valueOf(producto.getPrecio()));
            } else {
                actualizar.mostrarMensaje("Producto no encontrado");
            }
        } catch (Exception e) {
            actualizar.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void actualizarProducto() {
        try {
            int codigo = Integer.parseInt(actualizar.getTextField1().getText());
            String nombre = actualizar.getTextField2().getText();
            double precio = Double.parseDouble(actualizar.getTextField3().getText());

            Producto producto = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(producto);
            actualizar.mostrarMensaje("Producto actualizado correctamente");
        } catch (Exception e) {
            actualizar.mostrarMensaje("Error al actualizar: " + e.getMessage());
        }
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void buscarProductoEnCarrito() {
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);

            if (producto != null) {
                carritoAnadirView.getTextField2().setText(producto.getNombre());
                carritoAnadirView.getTextField3().setText(String.valueOf(producto.getPrecio()));
            } else {
                carritoAnadirView.getTextField2().setText("Producto no encontrado");
                carritoAnadirView.getTextField3().setText("");
            }
        } catch (Exception e) {
            carritoAnadirView.mostrarMensaje("Error: " + e.getMessage());
        }
    }
}
