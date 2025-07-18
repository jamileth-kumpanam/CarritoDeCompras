package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ResourceBundle;
/**
 * Ventana interna para eliminar productos existentes.
 * Permite buscar productos por código y eliminarlos.
 */
public class ProductoDeleteView extends JInternalFrame implements Idioma {

    private JPanel EliminarProductos;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblDelete;
    private JButton btnDeleteProducto;
    private JButton btnCancelar;
    private JLabel lblCodigo;

    private MensajeInternacionalizacionHandler mensajeHandler;
    private ProductoController productoController;

    public ProductoDeleteView(MensajeInternacionalizacionHandler handler) {
        this.mensajeHandler = handler;

        setTitle(mensajeHandler.get("producto.eliminar.titulo"));
        setContentPane(EliminarProductos);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);

        actualizarTextos(handler.getBundle());

        btnCancelar.addActionListener(e -> dispose());

        btnBuscar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (!codigo.matches("\\d+")) {
                mostrarMensaje(mensajeHandler.get("producto.datos.invalidos"));
                return;
            }
            if (productoController != null) {
                productoController.buscarProductoEliminar(Integer.parseInt(codigo), tblDelete);
            } else {
                mostrarMensaje("Controlador de productos no asignado.");
            }
        });

        btnDeleteProducto.addActionListener(e -> {
            int fila = tblDelete.getSelectedRow();
            if (fila == -1) {
                mostrarMensaje(mensajeHandler.get("producto.seleccione.fila"));
                return;
            }
            int codigo = Integer.parseInt(tblDelete.getValueAt(fila, 0).toString());
            if (productoController != null) {
                mostrarMensaje(mensajeHandler.get("producto.eliminado.exito"));
                txtCodigo.setText("");
                DefaultTableModel modelo = (DefaultTableModel) tblDelete.getModel();
                modelo.setRowCount(0);
            } else {
                mostrarMensaje("Controlador de productos no asignado.");
            }
        });
    }

    @Override
    public void actualizarTextos(ResourceBundle bundle) {
        setTitle(mensajeHandler.get("producto.eliminar.titulo"));
        if (lblCodigo != null) {
            lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        }
        if (btnBuscar != null) {
            btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        }
        if (btnDeleteProducto != null) {
            btnDeleteProducto.setText(mensajeHandler.get("producto.eliminar.boton"));
        }
        if (btnCancelar != null) {
            btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Getters y Setters

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblDelete() {
        return tblDelete;
    }

    public void setTblDelete(JTable tblDelete) {
        this.tblDelete = tblDelete;
    }

    public JButton getBtnDeleteProducto() {
        return btnDeleteProducto;
    }

    public void setBtnDeleteProducto(JButton btnDeleteProducto) {
        this.btnDeleteProducto = btnDeleteProducto;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public ProductoController getProductoController() {
        return productoController;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tblDelete.getModel();
        modelo.setRowCount(0);
    }

    public boolean mostrarMensajePregunta(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
}