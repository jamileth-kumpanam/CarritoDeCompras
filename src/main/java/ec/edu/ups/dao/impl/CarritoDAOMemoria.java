package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de CarritoDAO que almacena los carritos en memoria.
 */
public class CarritoDAOMemoria implements CarritoDAO {
    /** Lista interna que almacena los carritos en memoria. */
    private final List<Carrito> carritos = new ArrayList<>();
    /**
     * Agrega un nuevo carrito a la lista en memoria.
     * @param carrito Carrito a agregar.
     */
    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }
    /**
     * Busca un carrito por su código en la lista en memoria.
     * @param codigo Código del carrito a buscar.
     * @return Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        return carritos.stream()
                .filter(c -> c.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
    /**
     * Actualiza un carrito existente en la lista en memoria.
     * @param carrito Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        eliminar(carrito.getCodigo());
        crear(carrito);
    }
    /**
     * Elimina un carrito de la lista en memoria por su código.
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
    }
    /**
     * Guarda un carrito: si existe lo actualiza, si no lo crea.
     * @param carrito Carrito a guardar.
     */
    @Override
    public void guardar(Carrito carrito) {
        Carrito existente = buscarPorCodigo(carrito.getCodigo());
        if (existente != null) {
            actualizar(carrito);
        } else {
            crear(carrito);
        }
    }
    /**
     * Devuelve una copia de la lista de todos los carritos en memoria.
     * @return Lista de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }
}