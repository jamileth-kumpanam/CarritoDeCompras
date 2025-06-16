package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.ProductoAniadirView;
import ec.edu.ups.vista.ProductoDeleteView;
import ec.edu.ups.vista.ProductoListaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAniadirView productoAniadirView;
    private final ProductoListaView productoListaView;
    private final ProductoDeleteView productoDeleteView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAniadirView productoAnadirView,
                              ProductoListaView productoListaView, ProductoDeleteView productoDeleteView) {
        this.productoDAO = productoDAO;
        this.productoAniadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDeleteView = productoDeleteView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoAniadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
        productoDeleteView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAniadirView.getTxtCodigo().getText());
        String nombre = productoAniadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAniadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAniadirView.mostrarMensaje("Producto guardado correctamente");
        productoAniadirView.limpiarCampos();
        productoAniadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void eliminarProducto(){
        String codigo = productoDeleteView.getTextField1().getText(); // leer el texto
        int code = Integer.parseInt(codigo); // convertir a entero

        Producto productoDelete = productoDAO.buscarPorCodigo(code); // buscar el producto

        if (productoDelete != null) {
            productoDAO.eliminar(productoDelete.getCodigo()); // eliminar si lo encuentra
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

}