package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private ProductoEliminarView productoEliminarView;
    private ProductoAniadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoEliminarView productoDeleteView;
    private ActualizarProductos actualizar;
    private CarritoAnadirView carritoAnadirView;
    private ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAniadirView productoAnadirView,
                              ProductoListaView productoListaView, CarritoAnadirView carritoAnadirView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDeleteView = productoDeleteView;
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventos();
    }

    public ProductoController(ProductoAniadirView productoAnadirView, CarritoAnadirView carritoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosAniadir();
    }

    public ProductoController(ActualizarProductos actualizar, CarritoAnadirView carritoAnadirView) {
        this.actualizar = actualizar;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosActualizar();
    }

    public ProductoController(ProductoListaView productoListaView, CarritoAnadirView carritoAnadirView) {
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        configurarListas();
    }

    public ProductoController(ProductoEliminarView productoEliminarView, CarritoAnadirView carritoAnadirView) {
        this.productoEliminarView = productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
    }

    private void configurarEventosAniadir(){
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void  configurarEventosActualizar(){
        actualizar.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        actualizar.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoAc();
            }
        });
    }
    private void configurarListas(){
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
    }
    private void configurarEventos() {
        actualizar.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoAc();
            }
        });
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoDeleteView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoDelete();
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
        actualizar.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        carritoAnadirView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        /*carritoAnadirView.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProductoCarro();
            }
        });*/
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }
    /*private void guardarProductoT(){
        int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());
        String nombre = carritoAnadirView.getTextField2().getText();
        double precio = Double.parseDouble(carritoAnadirView.getTextField3().getText());
        int cantidad = carritoAnadirView.getComboCantidad().getSelectedIndex();

    }*/

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void buscarProductoDelete() {
        int codigo = Integer.parseInt(productoDeleteView.getTextField1().getText());

        Producto productosEncontrados = productoDAO.buscarPorCodigo(codigo);
        productoDeleteView.cargarDatos(productosEncontrados);
    }

    private void buscarPorCodigo(){
        int codigo = Integer.parseInt(carritoAnadirView.getTextField1().getText());

        Producto producto= productoDAO.buscarPorCodigo(codigo);
        if (producto== null){
            carritoAnadirView.getTextField2().setText("Producto no encontrado");
        }
        else {
            carritoAnadirView.getTextField2().setText(producto.getNombre());
            carritoAnadirView.getTextField3().setText(producto.getPrecio()+"");
        }
    }

    private void buscarPorCodigoAc(){
        int codigo = Integer.parseInt(actualizar.getTextField1().getText());
        Producto producto= productoDAO.buscarPorCodigo(codigo);
        if (producto== null){
            actualizar.mostrarMensaje("Producto no Encontrado");
        }
        else {
            actualizar.getTextNombreShow().setText(producto.getNombre());
            actualizar.getTextPrecioShow().setText(producto.getPrecio()+"");
        }
    }

    /*private void anadirProductoCarro(){
        carritoAnadirView.cargarDatos1();
    }*/

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void eliminarProducto(){
        String codigo = productoDeleteView.getTextField1().getText();
        int code = Integer.parseInt(codigo);

        Producto productoDelete = productoDAO.buscarPorCodigo(code);

        if (productoDelete != null) {
            productoDAO.eliminar(productoDelete.getCodigo());
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    private void actualizar(){
        String codigo = actualizar.getTextField1().getText();
        int code = Integer.parseInt(codigo);
        Producto productoAc = productoDAO.buscarPorCodigo(code);


        if (productoAc != null) {
            String nombre = actualizar.getTextField2().getText();
            double precio = Double.parseDouble(actualizar.getTextField3().getText());
            Producto productoAct = new Producto(code,nombre,precio);
            productoDAO.actualizar(productoAct);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

}