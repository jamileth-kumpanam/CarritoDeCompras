package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOArchivoBinario implements CarritoDAO {

    private final File archivo;

    public CarritoDAOArchivoBinario(String ruta) {
        this.archivo = new File(ruta + File.separator + "carritos.bin");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo binario", e);
        }
    }

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        carritos.add(carrito);
        guardarTodos(carritos);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listarTodos()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarTodos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarTodos(carritos);
    }

    @Override
    public void guardar(Carrito carrito) {

    }

    @Override
    public List<Carrito> listarTodos() {
        if (archivo.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Carrito>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void guardarTodos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar carritos en binario", e);
        }
    }
}