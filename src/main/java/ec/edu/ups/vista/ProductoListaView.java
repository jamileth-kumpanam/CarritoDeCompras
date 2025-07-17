package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ResourceBundle;

public class ProductoListaView extends JInternalFrame implements Idioma {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JButton cancelarButton;
    private JLabel lblNombre;

    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private ProductoController productoController;

    public ProductoListaView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;

        setTitle("Lista de Productos");
        setContentPane(panelPrincipal);
        setSize(500, 500);
        setClosable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio"}, 0);
        tblProductos.setModel(modelo);

        btnBuscar.addActionListener((ActionEvent e) -> {
            String nombre = txtBuscar.getText().trim();
            if (!nombre.isEmpty()) {
                if (productoController != null) {
                    List<Producto> resultado = productoController.buscarPorNombre(nombre);
                    cargarDatos(resultado);
                } else {
                    mostrarMensaje("Controlador de productos no asignado.");
                }
            } else {
                mostrarMensaje(mensajeHandler.get("producto.nombre.vacio"));
            }
        });

        btnListar.addActionListener((ActionEvent e) -> {
            if (productoController != null) {
                cargarDatos(productoController.obtenerTodos());
            } else {
                mostrarMensaje("Controlador de productos no asignado.");
            }
        });

        cancelarButton.addActionListener(e -> dispose());

        actualizarTextos(mensajeHandler.getBundle());
    }

    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setRowCount(0);
        for (Producto producto : listaProductos) {
            modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            });
        }
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        setTitle(mensajeHandler.get("producto.lista.titulo"));
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnListar.setText(mensajeHandler.get("boton.listar"));
        cancelarButton.setText(mensajeHandler.get("boton.cancelar"));
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public ProductoController getProductoController() {
        return productoController;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }
}