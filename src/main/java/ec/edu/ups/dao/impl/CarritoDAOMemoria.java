package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementación de CarritoDAO que almacena los carritos en memoria.
 * Se puede inicializar con una "ruta" (por compatibilidad), pero no se usa realmente.
 * Precarga algunos carritos al iniciar.
 */
public class CarritoDAOMemoria implements CarritoDAO {

    private final List<Carrito> carritos;

    /**
     * Constructor sin ruta (por compatibilidad).
     */
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
        precargarCarritos();
    }

    /**
     * Constructor con ruta (no usado pero por consistencia con otros DAO).
     * @param ruta Ruta de archivos (ignorada).
     */
    public CarritoDAOMemoria(String ruta) {
        this.carritos = new ArrayList<>();
        precargarCarritos();
    }

    /**
     * Precarga carritos básicos en memoria para pruebas iniciales.
     */
    private void precargarCarritos() {
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("admin");

        Producto producto1 = new Producto(1, "Producto 1", 10.0);
        Producto producto2 = new Producto(2, "Producto 2", 20.0);

        List<ItemCarrito> items1 = new ArrayList<>();
        items1.add(new ItemCarrito(producto1, 2));
        items1.add(new ItemCarrito(producto2, 1));

        Carrito carrito1 = new Carrito(new GregorianCalendar(), items1, usuario1);
        carrito1.setCodigo(1);

        carritos.add(carrito1);
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        return carritos.stream()
                .filter(c -> c.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizar(Carrito carrito) {
        eliminar(carrito.getCodigo());
        crear(carrito);
    }

    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
    }

    @Override
    public void guardar(Carrito carrito) {
        Carrito existente = buscarPorCodigo(carrito.getCodigo());
        if (existente != null) {
            actualizar(carrito);
        } else {
            crear(carrito);
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }
}
