package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class ProductoDeleteView extends JInternalFrame {

    private JPanel EliminarProductos;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblDelete;
    private JButton btnDeleteProducto;

    public ProductoDeleteView() {
        setContentPane(EliminarProductos);
        setTitle("Eliminar Producto");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
    }

    public ProductoDeleteView(ProductoController productoController, MensajeInternacionalizacionHandler mensajeHandler) {
    }

    public JPanel getEliminarProductos() {
        return EliminarProductos;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTable getTblDelete() {
        return tblDelete;
    }

    public JButton getBtnDeleteProducto() {
        return btnDeleteProducto;
    }

    public void setEliminarProductos(JPanel eliminarProductos) {
        EliminarProductos = eliminarProductos;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public void setTblDelete(JTable tblDelete) {
        this.tblDelete = tblDelete;
    }

    public void setBtnDeleteProducto(JButton btnDeleteProducto) {
        this.btnDeleteProducto = btnDeleteProducto;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
    }

    public boolean mostrarMensajePregunta(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
}
