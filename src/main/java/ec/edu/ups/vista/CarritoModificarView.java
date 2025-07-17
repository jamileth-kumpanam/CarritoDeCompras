package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.ResourceBundle;

public class CarritoModificarView extends JInternalFrame implements Idioma {

    private JPanel panelPrincipal;
    private JButton buscarButton;
    private JTextField codigoTextField;
    private JTextField nombreTextField;
    private JTextField precioTextField;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTable productosTable;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JComboBox<String> cantidadComboBox;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JLabel codigoLabel;
    private JLabel nombreLabel;
    private JLabel precioLabel;
    private JLabel cantidadLabel;
    private JLabel lblProducto;
    private JLabel lblTotal;
    private JLabel lblIva;
    private JLabel lblSubtotal;
    private JLabel lblElegirCarro;
    private JComboBox cbxElegirCarrito;
    private JComboBox cbxIngrProducto;
    private CarritoController carritoController;
    private ProductoController productoController;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoModificarView(CarritoController carritoController, ProductoController productoController, MensajeInternacionalizacionHandler mensajeHandler) {
        super("Modificar Carrito", true, true, false, true);
        this.carritoController = carritoController;
        this.productoController = productoController;
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        actualizarTextos(mensajeHandler.getBundle());

        cancelarButton.addActionListener(e -> dispose());
        limpiarButton.addActionListener(e -> limpiarCampos());
        configurarEventos();
    }

    private void configurarEventos() {
        if (buscarButton != null) buscarButton.addActionListener(e -> carritoController.buscarProducto());
        if (modificarButton != null) modificarButton.addActionListener(e -> carritoController.modificarProductoEnCarrito());
        if (eliminarButton != null) eliminarButton.addActionListener(e -> carritoController.eliminarProductoDelCarrito());
        if (guardarButton != null) guardarButton.addActionListener(e -> carritoController.guardarCarrito());
    }

    public void limpiarCampos() {
        codigoTextField.setText("");
        nombreTextField.setText("");
        precioTextField.setText("");
        subtotalTextField.setText("");
        ivaTextField.setText("");
        totalTextField.setText("");
        cantidadComboBox.setSelectedIndex(0);
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        codigoLabel.setText(mensajeHandler.get("login.Codigo"));
        nombreLabel.setText(mensajeHandler.get("login.Nombre"));
        precioLabel.setText(mensajeHandler.get("login.Precio"));
        cantidadLabel.setText(mensajeHandler.get("login.Cantidad"));

        buscarButton.setText(mensajeHandler.get("login.Buscar"));
        modificarButton.setText(mensajeHandler.get("login.modificar"));
        eliminarButton.setText(mensajeHandler.get("login.eliminar"));
        guardarButton.setText(mensajeHandler.get("login.guardar"));
        limpiarButton.setText(mensajeHandler.get("login.limpiar"));
        cancelarButton.setText(mensajeHandler.get("login.cancelar"));
    }

    // Getters y Setters

    public JButton getBuscarButton() { return buscarButton; }
    public JTextField getCodigoTextField() { return codigoTextField; }
    public JTextField getNombreTextField() { return nombreTextField; }
    public JTextField getPrecioTextField() { return precioTextField; }
    public JButton getModificarButton() { return modificarButton; }
    public JButton getEliminarButton() { return eliminarButton; }
    public JTable getProductosTable() { return productosTable; }
    public JTextField getSubtotalTextField() { return subtotalTextField; }
    public JTextField getIvaTextField() { return ivaTextField; }
    public JTextField getTotalTextField() { return totalTextField; }
    public JButton getGuardarButton() { return guardarButton; }
    public JButton getLimpiarButton() { return limpiarButton; }
    public JComboBox<String> getCantidadComboBox() { return cantidadComboBox; }
    public JButton getCancelarButton() { return cancelarButton; }
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }
    public void setCodigoTextField(JTextField codigoTextField) {
        this.codigoTextField = codigoTextField;
    }
    public void setNombreTextField(JTextField nombreTextField) {
        this.nombreTextField = nombreTextField;
    }
    public void setPrecioTextField(JTextField precioTextField) {
        this.precioTextField = precioTextField;
    }
    public void setModificarButton(JButton modificarButton) {
        this.modificarButton = modificarButton;
    }
    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }
    public void setCarritoController(CarritoController carritoController) {
        this.carritoController = carritoController;
    }
    public void setCbxIngrProducto(JComboBox cbxIngrProducto) {
        this.cbxIngrProducto = cbxIngrProducto;
    }
    public void setCbxElegirCarrito(JComboBox cbxElegirCarrito) {
        this.cbxElegirCarrito = cbxElegirCarrito;
    }
    public void setLblElegirCarro(JLabel lblElegirCarro) {
        this.lblElegirCarro = lblElegirCarro;
    }
    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }
    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }
    public void setLblProducto(JLabel lblProducto) {
        this.lblProducto = lblProducto;
    }
    public void setCantidadLabel(JLabel cantidadLabel) {
        this.cantidadLabel = cantidadLabel;
    }
    public void setPrecioLabel(JLabel precioLabel) {
        this.precioLabel = precioLabel;
    }
    public void setNombreLabel(JLabel nombreLabel) {
        this.nombreLabel = nombreLabel;
    }
    public void setCodigoLabel(JLabel codigoLabel) {
        this.codigoLabel = codigoLabel;
    }
    public void setLimpiarButton(JButton limpiarButton) {
        this.limpiarButton = limpiarButton;
    }
    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
    }
    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }
    public void setCantidadComboBox(JComboBox<String> cantidadComboBox) {
        this.cantidadComboBox = cantidadComboBox;
    }
    public void setTotalTextField(JTextField totalTextField) {
        this.totalTextField = totalTextField;
    }
    public void setIvaTextField(JTextField ivaTextField) {
        this.ivaTextField = ivaTextField;
    }
    public void setSubtotalTextField(JTextField subtotalTextField) {
        this.subtotalTextField = subtotalTextField;
    }
    public void setProductosTable(JTable productosTable) {
        this.productosTable = productosTable;
    }
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }
    public ProductoController getProductoController() {
        return productoController;
    }
    public CarritoController getCarritoController() {
        return carritoController;
    }
    public JComboBox getCbxIngrProducto() {
        return cbxIngrProducto;
    }
    public JComboBox getCbxElegirCarrito() {
        return cbxElegirCarrito;
    }
    public JLabel getLblElegirCarro() {
        return lblElegirCarro;
    }
    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }
    public JLabel getLblIva() {
        return lblIva;
    }
    public JLabel getLblTotal() {
        return lblTotal;
    }
    public JLabel getLblProducto() {
        return lblProducto;
    }
    public JLabel getCantidadLabel() {
        return cantidadLabel;
    }
    public JLabel getPrecioLabel() {
        return precioLabel;
    }
    public JLabel getNombreLabel() {
        return nombreLabel;
    }
    public JLabel getCodigoLabel() {
        return codigoLabel;
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}