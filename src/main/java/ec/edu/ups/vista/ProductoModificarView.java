package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class ProductoModificarView extends JInternalFrame implements Idioma {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnActualizar;
    private JButton btnLimpiar;
    private JButton btnCancelar;
    private JPanel ModificarProducto;
    private JButton btnBuscar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;

    private MensajeInternacionalizacionHandler mensajeHandler;
    private ProductoController productoController;

    public ProductoModificarView(ProductoController controller, MensajeInternacionalizacionHandler handler) {
        this.productoController = controller;
        this.mensajeHandler = handler;

        setTitle("Modificar Producto");
        setContentPane(ModificarProducto);
        setTitle("Lista de Productos");
        setSize(500, 500);
        setClosable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLimpiar.addActionListener((ActionEvent e) -> limpiarCampos());
        btnCancelar.addActionListener((ActionEvent e) -> dispose());

        actualizarTextos(mensajeHandler.getBundle());
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        setTitle(mensajeHandler.get("producto.modificar.titulo"));
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.precio"));
        btnActualizar.setText(mensajeHandler.get("boton.actualizar"));
        btnLimpiar.setText(mensajeHandler.get("boton.limpiar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JPanel getModificarProducto() {
        return ModificarProducto;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean mostrarMensajePregunta(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }
}