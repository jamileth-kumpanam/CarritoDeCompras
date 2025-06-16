package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoAniadirView productoView = new ProductoAniadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoDeleteView productoDeleteView = new ProductoDeleteView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                new ProductoController(productoDAO, productoView, productoListaView, productoDeleteView);
            }
        });
    }
}
