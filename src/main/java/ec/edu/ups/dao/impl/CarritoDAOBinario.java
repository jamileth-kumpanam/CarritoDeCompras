package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOBinario implements CarritoDAO {
    private final String archivo;

    public CarritoDAOBinario(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listar();
        carritos.add(carrito);
        guardarCarritos(carritos);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listar()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    public Carrito buscarPorId(String id) {
        int codigo = Integer.parseInt(id);
        return buscarPorCodigo(codigo);
    }

    public List<Carrito> listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Carrito>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listar();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarCarritos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listar();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }

    public void eliminar(String id) {
        int codigo = Integer.parseInt(id);
        eliminar(codigo);
    }

    @Override
    public void guardar(Carrito carrito) {
        crear(carrito);
    }

    @Override
    public List<Carrito> listarTodos() {
        return listar();
    }

    private void guardarCarritos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}