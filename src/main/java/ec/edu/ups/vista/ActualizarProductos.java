package ec.edu.ups.vista;

import javax.swing.*;

public class ActualizarProductos extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField Idtxt;
    private JTextField nombre2txt;
    private JTextField precio2txt;
    private JButton cancelarButton;
    private JButton modificarButton;
    private JButton buscarButton;
    private JTextField nombretxt;
    private JTextField preciotxt;

    public ActualizarProductos(){
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        //setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        setVisible(false);

        //pack();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTextNombreShow() {
        return nombretxt;
    }

    public void setTextNombreShow(JTextField textNombreShow) {
        this.nombretxt = textNombreShow;
    }

    public JTextField getTextPrecioShow() {
        return preciotxt;
    }

    public void setTextPrecioShow(JTextField textPrecioShow) {
        this.preciotxt = textPrecioShow;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField1() {
        return Idtxt;
    }

    public void setTextField1(JTextField textField1) {
        this.Idtxt = textField1;
    }

    public JTextField getTextField2() {
        return nombre2txt;
    }

    public void setTextField2(JTextField textField2) {
        this.nombre2txt = textField2;
    }

    public JTextField getTextField3() {
        return precio2txt;
    }

    public void setTextField3(JTextField textField3) {
        this.precio2txt = textField3;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public void setModificarButton(JButton modificarButton) {
        this.modificarButton = modificarButton;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

}
