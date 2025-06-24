package ec.edu.ups;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.ProductoAniadirView;
import ec.edu.ups.vista.ProductoListaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                //instanciamos DAO (Singleton)
                ProductoDAO productoDAO = new ProductoDAOMemoria();

                //instancio Vistas
                MenuPrincipalView principalView = new MenuPrincipalView();
                ProductoAniadirView productoAnadirView = new ProductoAniadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();


                //instanciamos Controladores
                ProductoController productoController = new ProductoController(productoDAO,
                        productoAnadirView, productoListaView, carritoAnadirView);

                principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!productoAnadirView.isVisible()){
                            productoAnadirView.setVisible(true);
                            principalView.getjDesktopPane().add(productoAnadirView);
                        }
                    }
                });

                principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!productoListaView.isVisible()){
                            productoListaView.setVisible(true);
                            principalView.getjDesktopPane().add(productoListaView);
                        }
                    }
                });

                principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!carritoAnadirView.isVisible()){
                            carritoAnadirView.setVisible(true);
                            principalView.getjDesktopPane().add(carritoAnadirView);
                        }
                    }
                });
            }
        });
    }
}