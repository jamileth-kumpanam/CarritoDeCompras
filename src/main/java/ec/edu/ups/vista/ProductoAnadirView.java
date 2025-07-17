package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class ProductoAnadirView extends JInternalFrame implements Idioma {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JButton btnCancelar;
    private JLabel lblPrecio;
    private JLabel lblNombre;
    private JLabel lblCodigo;
    private JLabel lblTitulo;

    private MensajeInternacionalizacionHandler mensajeHandler;
    private ProductoController productoController;

    public ProductoAnadirView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;

        setTitle(mensajeHandler.get("producto.titulo"));
        setContentPane(panelPrincipal);
        setSize(400, 300);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        actualizarTextos(handler.getBundle());

        btnLimpiar.addActionListener((ActionEvent e) -> limpiarCampos());

        btnAceptar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (!codigo.matches("\\d+") || !precioStr.matches("\\d+(\\.\\d+)?")) {
                mostrarMensaje(mensajeHandler.get("producto.datos.invalidos"));
                return;
            }

            int codigoInt = Integer.parseInt(codigo);
            double precio = Double.parseDouble(precioStr);

            Producto producto = new Producto(codigoInt, nombre, precio);
            if (productoController != null) {
                productoController.agregarProducto(producto);
                mostrarMensaje(mensajeHandler.get("producto.guardado.exito"));
                limpiarCampos();
            } else {
                mostrarMensaje("Controlador de productos no asignado.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        if (lblTitulo != null) {
            lblTitulo.setText(mensajeHandler.get("producto.titulo"));
        }
        if (lblCodigo != null) {
            lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        }
        if (lblNombre != null) {
            lblNombre.setText(mensajeHandler.get("producto.nombre"));
        }
        if (lblPrecio != null) {
            lblPrecio.setText(mensajeHandler.get("producto.precio"));
        }
        if (btnAceptar != null) {
            btnAceptar.setText(mensajeHandler.get("boton.guardar"));
        }
        if (btnCancelar != null) {
            btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
        }
        if (btnLimpiar != null) {
            btnLimpiar.setText(mensajeHandler.get("boton.limpiar"));
        }
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
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

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

}