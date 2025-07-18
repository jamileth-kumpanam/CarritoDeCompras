package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de CarritoDAO que almacena los carritos en un archivo binario.
 */
public class CarritoDAOBinario implements CarritoDAO {
    private final String archivo;
    /**
     * Constructor que recibe la ruta del archivo binario.
     * @param archivo Ruta del archivo binario.
     */
    public CarritoDAOBinario(String archivo) {
        this.archivo = archivo;
    }
    /**
     * Crea un nuevo carrito y lo agrega al archivo binario.
     * @param carrito Carrito a crear.
     */
    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listar();
        carritos.add(carrito);
        guardarCarritos(carritos);
    }
    /**
     * Busca un carrito por su código en el archivo binario.
     * @param codigo Código del carrito.
     * @return Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listar()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }
    /**
     * Busca un carrito por su id (como String) en el archivo binario.
     * @param id Id del carrito.
     * @return Carrito encontrado o null.
     */
    public Carrito buscarPorId(String id) {
        int codigo = Integer.parseInt(id);
        return buscarPorCodigo(codigo);
    }
    /**
     * Lista todos los carritos almacenados en el archivo binario.
     * @return Lista de carritos.
     */
    public List<Carrito> listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Carrito>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    /**
     * Actualiza un carrito existente en el archivo binario.
     * @param carrito Carrito con los datos actualizados.
     */
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
    /**
     * Elimina un carrito del archivo binario por su código.
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listar();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }
    /**
     * Elimina un carrito del archivo binario por su id (como String).
     * @param id Id del carrito a eliminar.
     */
    public void eliminar(String id) {
        int codigo = Integer.parseInt(id);
        eliminar(codigo);
    }
    /**
     * Guarda un carrito en el archivo binario (crear o actualizar).
     * @param carrito Carrito a guardar.
     */
    @Override
    public void guardar(Carrito carrito) {
        crear(carrito);
    }
    /**
     * Devuelve la lista de todos los carritos almacenados en el archivo binario.
     * @return Lista de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return listar();
    }
    /**
     * Guarda la lista de carritos en el archivo binario.
     * @param carritos Lista de carritos a guardar.
     */
    private void guardarCarritos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}