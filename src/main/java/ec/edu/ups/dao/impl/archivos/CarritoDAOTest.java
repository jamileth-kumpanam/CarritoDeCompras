package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoDAOTest {
    public static void main(String[] args) {
        // Cambia la ruta según tu proyecto
        String ruta = "datos";
        // Prueba con archivo de texto
        CarritoDAO daoTexto = new CarritoDAOArchivoTexto(ruta);
        // Prueba con archivo binario
        CarritoDAO daoBinario = new CarritoDAOArchivoBinario(ruta);

        // Crear datos de prueba
        Usuario usuario = new Usuario(1, "Juan", "juan123", "clave123");
        Producto producto = new Producto(1, "Camiseta", 10.0);
        List<ItemCarrito> items = new ArrayList<>();
        items.add(new ItemCarrito(producto, 2));
        Carrito carrito = new Carrito(new GregorianCalendar(), items, usuario);
        carrito.setCodigo(100);

        // Guardar y leer en texto
        daoTexto.crear(carrito);
        System.out.println("Carritos en texto:");
        for (Carrito c : daoTexto.listarTodos()) {
            System.out.println(c);
        }

        // Guardar y leer en binario
        daoBinario.crear(carrito);
        System.out.println("Carritos en binario:");
        for (Carrito c : daoBinario.listarTodos()) {
            System.out.println(c);
        }
    }
}