package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class CarritoListaView extends JInternalFrame {

    private JPanel ListaCarroCompras;
    private JTable tblListaCarrito;

    public CarritoListaView() {
        setContentPane(ListaCarroCompras);
        setTitle("Lista del Carrito de Compras");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
    }

    public CarritoListaView(CarritoController carritoController, MensajeInternacionalizacionHandler mensajeHandler) {
    }

    public JPanel getListaCarroCompras() {
        return ListaCarroCompras;
    }

    public JTable getTblListaCarrito() {
        return tblListaCarrito;
    }

    public void setListaCarroCompras(JPanel listaCarroCompras) {
        ListaCarroCompras = listaCarroCompras;
    }

    public void setTblListaCarrito(JTable tblListaCarrito) {
        this.tblListaCarrito = tblListaCarrito;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

