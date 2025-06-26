package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTable table1;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JTextField codigo;
    private JTextField nombre;
    private JTextField precio;
    private JTextField textCantidad;
    private JTextField subTotaltxt;
    private JTextField iva;
    private JTextField total;
    private JButton buscarButton;
    private JButton agregarButton;
    private JComboBox cantidad;
    private JTextField textFecha;
    private JLabel codigotxt;
    private JLabel nombretxt;
    private JLabel ptrciotxt;
    private JLabel cantidadtxt;
    private JLabel fechatxt;
    private JLabel subtotaltxt;
    private JLabel ivatxt;
    private JLabel totaltxt;
    private DefaultTableModel modelo;

    public CarritoAnadirView(){
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        //setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        setVisible(false);
        cargarDatos();

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }


    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public JTextField getTextField1() {
        return codigo;
    }

    public void setTextField1(JTextField textField1) {
        this.codigo = textField1;
    }

    public JTextField getTextField2() {
        return nombre;
    }

    public void setTextField2(JTextField textField2) {
        this.nombre = textField2;
    }

    public JTextField getTextField3() {
        return precio;
    }

    public void setTextField3(JTextField textField3) {
        this.precio = textField3;
    }

    public JTextField getTextField4() {
        return textCantidad;
    }

    public void setTextField4(JTextField textField4) {
        this.textCantidad = textField4;
    }

    public JTextField getTextField5() {
        return subTotaltxt;
    }

    public void setTextField5(JTextField textField5) {
        this.subTotaltxt = textField5;
    }

    public JTextField getTextField6() {
        return iva;
    }

    public void setTextField6(JTextField textField6) {
        this.iva = textField6;
    }

    public JTextField getTextField7() {
        return total;
    }

    public void setTextField7(JTextField textField7) {
        this.total = textField7;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JButton getAgregarButton() {
        return agregarButton;
    }

    public JTextField getTextFecha() {
        return textFecha;
    }

    public void setTextFecha(JTextField textFecha) {
        this.textFecha = textFecha;
    }

    public void setAgregarButton(JButton agregarButton) {
        this.agregarButton = agregarButton;
    }

    public JTextField getTextCodigo() {
        return codigo;
    }

    public void setTextCodigo(JTextField textCodigo) {
        this.codigo = textCodigo;
    }

    public JTextField getTextCantidad() {
        return textCantidad;
    }

    public void setTextCantidad(JTextField textCantidad) {
        this.textCantidad = textCantidad;
    }

    public JTextField getTextSubtotal() {
        return subTotaltxt;
    }

    public void setTextSubtotal(JTextField textSubtotal) {
        this.subTotaltxt = textSubtotal;
    }

    public JTextField getTextIVA() {
        return iva;
    }

    public void setTextIVA(JTextField textIVA) {
        this.iva = textIVA;
    }

    public JTextField getTextTotal() {
        return total;
    }

    public void setTextTotal(JTextField textTotal) {
        this.total = textTotal;
    }

    public JComboBox getComboCantidad() {
        return cantidad;
    }

    public void setComboCantidad(JComboBox comboCantidad) {
        this.cantidad = comboCantidad;
    }

    private void cargarDatos(){
        cantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cantidad.addItem(String.valueOf(i + 1));
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

}
