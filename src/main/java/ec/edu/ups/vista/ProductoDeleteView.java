package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class ProductoDeleteView extends JInternalFrame implements Idioma {

    private JPanel EliminarProductos;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblDelete;
    private JButton btnDeleteProducto;
    private JButton btnCancelar;
    private JLabel lblCodigo;
    private JLabel lblTitulo;

    private MensajeInternacionalizacionHandler mensajeHandler;
    private ProductoController productoController;

    public ProductoDeleteView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;

        setTitle(mensajeHandler.get("producto.eliminar.titulo"));
        setContentPane(EliminarProductos);
        setSize(500, 500);
        setClosable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(100, 100);

        actualizarTextos(handler.getBundle());

        btnCancelar.addActionListener(e -> dispose());

        btnDeleteProducto.addActionListener((ActionEvent e) -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                mostrarMensaje(mensajeHandler.get("producto.codigo.vacio"));
                return;
            }

            boolean confirmado = mostrarMensajePregunta(mensajeHandler.get("producto.confirmar.eliminar"));
            if (confirmado) {
                boolean eliminado = productoController.eliminarProductoPorCodigo(codigo);
                if (eliminado) {
                    mostrarMensaje(mensajeHandler.get("producto.eliminado.exito"));
                    limpiarCampos();
                } else {
                    mostrarMensaje(mensajeHandler.get("producto.no.encontrado"));
                }
            }
        });

        btnBuscar.addActionListener(e -> {
        });
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        lblTitulo.setText(mensajeHandler.get("producto.eliminar.titulo"));
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnDeleteProducto.setText(mensajeHandler.get("boton.eliminar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
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
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnDeleteProducto() {
        return btnDeleteProducto;
    }

    public JPanel getEliminarProductos() {
        return EliminarProductos;
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

    public JTable getTblDelete() {
        return tblDelete;
    }

    public void setBtnDeleteProducto(JButton btnDeleteProducto) {
        this.btnDeleteProducto = btnDeleteProducto;
    }

    public void setTblDelete(JTable tblDelete) {
        this.tblDelete = tblDelete;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
}
