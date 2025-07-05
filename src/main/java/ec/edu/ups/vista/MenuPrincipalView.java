package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {

    private MiJDesktopPane jDesktopPane;

    public MenuPrincipalView(
            UsuarioController usuarioController,
            ProductoController productoController,
            CarritoController carritoController,
            MensajeInternacionalizacionHandler mensajeHandler
    ) {
        setTitle("Menú Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jDesktopPane = new MiJDesktopPane();
        setContentPane(jDesktopPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuUsuario = new JMenu("Usuario");
        JMenuItem itemRegistro = new JMenuItem("Registrar");
        itemRegistro.addActionListener(e -> {
            UsuarioRegistroView view = new UsuarioRegistroView(usuarioController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemRecuperar = new JMenuItem("Recuperar Contraseña");
        itemRecuperar.addActionListener(e -> {
            PreguntasContraseniaView view = new PreguntasContraseniaView(usuarioController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemCambiar = new JMenuItem("Cambiar Contraseña");
        itemCambiar.addActionListener(e -> {
            CambioContraseniaView view = new CambioContraseniaView(usuarioController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        menuUsuario.add(itemRegistro);
        menuUsuario.add(itemRecuperar);
        menuUsuario.add(itemCambiar);

        JMenu menuProducto = new JMenu("Producto");
        JMenuItem itemAnadir = new JMenuItem("Añadir");
        itemAnadir.addActionListener(e -> {
            ProductoAnadirView view = new ProductoAnadirView(productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemModificar = new JMenuItem("Modificar");
        itemModificar.addActionListener(e -> {
            ProductoModificarView view = new ProductoModificarView(productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        itemEliminar.addActionListener(e -> {
            ProductoDeleteView view = new ProductoDeleteView(productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemListar = new JMenuItem("Listar");
        itemListar.addActionListener(e -> {
            ProductoListaView view = new ProductoListaView(productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        menuProducto.add(itemAnadir);
        menuProducto.add(itemModificar);
        menuProducto.add(itemEliminar);
        menuProducto.add(itemListar);

        JMenu menuCarrito = new JMenu("Carrito");
        JMenuItem itemAnadirCarrito = new JMenuItem("Añadir");
        itemAnadirCarrito.addActionListener(e -> {
            CarritoAnadirView view = new CarritoAnadirView(carritoController, productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemModificarCarrito = new JMenuItem("Modificar");
        itemModificarCarrito.addActionListener(e -> {
            CarritoModificarView view = new CarritoModificarView(carritoController, productoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        JMenuItem itemListarCarrito = new JMenuItem("Listar");
        itemListarCarrito.addActionListener(e -> {
            CarritoListaView view = new CarritoListaView(carritoController, mensajeHandler);
            jDesktopPane.add(view);
            view.setVisible(true);
        });
        menuCarrito.add(itemAnadirCarrito);
        menuCarrito.add(itemModificarCarrito);
        menuCarrito.add(itemListarCarrito);

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem itemCerrar = new JMenuItem("Cerrar");
        itemCerrar.addActionListener(e -> System.exit(0));
        menuSalir.add(itemCerrar);

        menuBar.add(menuUsuario);
        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);
        pack();
    }
}
