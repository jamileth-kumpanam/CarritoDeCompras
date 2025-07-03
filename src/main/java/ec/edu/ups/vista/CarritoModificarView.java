package ec.edu.ups.vista;

import javax.swing.*;

public class CarritoModificarView extends JInternalFrame {

    private JPanel ModificarCarrito;
    private JComboBox cbxElegirCarrito;
    private JTable table1;
    private JButton btnEliminar;
    private JComboBox cbxSelCantidad;
    private JTextField txtIngrProducto;
    private JTextField txtIngrCodProducto;
    private JComboBox cbxIngrProducto;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;

    public CarritoModificarView() {
        setContentPane(ModificarCarrito);
        setTitle("Modificar Carrito");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
    }

    public JPanel getModificarCarrito() {
        return ModificarCarrito;
    }

    public JComboBox getCbxElegirCarrito() {
        return cbxElegirCarrito;
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JComboBox getCbxSelCantidad() {
        return cbxSelCantidad;
    }

    public JTextField getTxtIngrProducto() {
        return txtIngrProducto;
    }

    public JTextField getTxtIngrCodProducto() {
        return txtIngrCodProducto;
    }

    public JComboBox getCbxIngrProducto() {
        return cbxIngrProducto;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setModificarCarrito(JPanel modificarCarrito) {
        ModificarCarrito = modificarCarrito;
    }

    public void setCbxElegirCarrito(JComboBox cbxElegirCarrito) {
        this.cbxElegirCarrito = cbxElegirCarrito;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public void setCbxSelCantidad(JComboBox cbxSelCantidad) {
        this.cbxSelCantidad = cbxSelCantidad;
    }

    public void setTxtIngrProducto(JTextField txtIngrProducto) {
        this.txtIngrProducto = txtIngrProducto;
    }

    public void setTxtIngrCodProducto(JTextField txtIngrCodProducto) {
        this.txtIngrCodProducto = txtIngrCodProducto;
    }

    public void setCbxIngrProducto(JComboBox cbxIngrProducto) {
        this.cbxIngrProducto = cbxIngrProducto;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public void setTxtSubtotal(JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtIngrProducto.setText("");
        txtIngrCodProducto.setText("");
        cbxSelCantidad.setSelectedIndex(0);
        cbxIngrProducto.setSelectedIndex(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }
}
