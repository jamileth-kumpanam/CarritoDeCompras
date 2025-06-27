
package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController{
    private CarritoDAO carritoDAO;
    private CarritoAnadirView carritoAnadirView;
    private ProductoDAO productoDAO;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carrito = new Carrito();
        configurarEventosVistas();
    }

    private void configurarEventosVistas(){


        carritoAnadirView.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();

            }
        });
        carritoAnadirView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });
        carritoAnadirView.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }
    private void anadirProducto(){
        Producto producto = productoDAO.buscarPorCodigo(Integer.parseInt(carritoAnadirView.getTextCodigo().getText()));
        int cantidad = Integer.parseInt(carritoAnadirView.getComboCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto,cantidad);

        cargarProductos();
        mostrarTotales();

    }
    private void cargarProductos(){
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo= (DefaultTableModel) carritoAnadirView.getTable1().getModel();
        modelo.setNumRows(0);
        for(ItemCarrito item : items){
            modelo.addRow(new Object[]{item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio()* item.getCantidad()});

        }
    }
    private void guardarCarrito(){
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito creado completamente");
        System.out.println(carritoDAO.listarTodos());
    }
    private void mostrarTotales(){
        String subtotal = String.valueOf(carrito.calcularSubtotal());
        String iva = String.valueOf(carrito.calcularIVA());
        String total = String.valueOf(carrito.calcularTotal());

        carritoAnadirView.getTextSubtotal().setText(subtotal);
        carritoAnadirView.getTextIVA().setText(iva);
        carritoAnadirView.getTextTotal().setText(total);
    }

    private void cancelarCarrito(){

    }

}
