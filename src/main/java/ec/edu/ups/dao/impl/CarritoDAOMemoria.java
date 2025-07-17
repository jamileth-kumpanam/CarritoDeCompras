package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    private final List<Carrito> carritos = new ArrayList<>();

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