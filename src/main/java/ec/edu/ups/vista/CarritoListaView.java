package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.util.Idioma;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class CarritoListaView extends JInternalFrame implements Idioma {

    private MensajeInternacionalizacionHandler mensajeHandler;
    private JPanel ListaCarroCompras;
    private JTable tblListaCarrito;
    private JButton btnCancelar;
    private JLabel lblListaCarros;
    private CarritoController carritoController;
    private DefaultTableModel modelo;

    public CarritoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        // Inicializa los componentes manualmente (si usas un GUI builder, ignora o ajusta)
        ListaCarroCompras = new JPanel(new BorderLayout());
        lblListaCarros = new JLabel();
        tblListaCarrito = new JTable();
        btnCancelar = new JButton();

        // Panel superior con etiqueta
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(lblListaCarros);
        ListaCarroCompras.add(panelSuperior, BorderLayout.NORTH);

        // Tabla en el centro con scroll
        JScrollPane scrollPane = new JScrollPane(tblListaCarrito);
        ListaCarroCompras.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botón cancelar
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnCancelar);
        ListaCarroCompras.add(panelInferior, BorderLayout.SOUTH);

        // Configura la ventana interna
        setContentPane(ListaCarroCompras);
        setTitle("Carritos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Configura tabla
        modelo = new DefaultTableModel();
        Object[] columnas = {"ID", "Fecha Creación", "Usuario", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblListaCarrito.setModel(modelo);

        // Carga textos iniciales
        actualizarTextos(mensajeHandler.getBundle());

        // Icono para el botón cancelar
        ImageIcon iconCancelar = new ImageIcon(getClass().getResource("/imagenes/cross (1).png"));
        btnCancelar.setIcon(new ImageIcon(iconCancelar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        // Acción botón cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public CarritoListaView(CarritoController carritoController, MensajeInternacionalizacionHandler mensajeHandler) {
        this(mensajeHandler);
        this.carritoController = carritoController;
    }

    // Getters y setters

    public JPanel getListaCarroCompras() {
        return ListaCarroCompras;
    }

    public void setListaCarroCompras(JPanel listaCarroCompras) {
        this.ListaCarroCompras = listaCarroCompras;
    }

    public JTable getTblListaCarrito() {
        return tblListaCarrito;
    }

    public void setTblListaCarrito(JTable tblListaCarrito) {
        this.tblListaCarrito = tblListaCarrito;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JLabel getLblListaCarros() {
        return lblListaCarros;
    }

    public void setLblListaCarros(JLabel lblListaCarros) {
        this.lblListaCarros = lblListaCarros;
    }

    public CarritoController getCarritoController() {
        return carritoController;
    }

    public void setCarritoController(CarritoController carritoController) {
        this.carritoController = carritoController;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public int obtenerCodigoCarritoSeleccionado() {
        int fila = tblListaCarrito.getSelectedRow();
        if (fila >= 0) {
            return (int) modelo.getValueAt(fila, 0);
        }
        return -1;
    }

    public void actualizarTextos(ResourceBundle bundle) {
        lblListaCarros.setText(mensajeHandler.get("titulo"));
        btnCancelar.setText(mensajeHandler.get("cancelar"));
    }
}
