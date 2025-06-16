package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoListaView extends JFrame {
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JTable tblProductos;
    private JButton listarButton;
    private JPanel panelPrincipal;

    public ProductoListaView() {
        // Inicializar componentes
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
        tblProductos = new JTable();
        panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior para buscar
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);
        panelBuscar.add(btnLimpiar);

        // Scroll para tabla
        JScrollPane scrollPane = new JScrollPane(tblProductos);

        // Añadir al panel principal
        panelPrincipal.add(panelBuscar, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Configurar JFrame
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        // Acción del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtBuscar.setText("");
        // Si necesitas limpiar la tabla también, puedes agregarlo aquí
    }

    // Getters y setters
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public Button getBtnListar() {
        return null;
    }

    public void cargarDatos(List<Producto> productosEncontrados) {
    }
}

