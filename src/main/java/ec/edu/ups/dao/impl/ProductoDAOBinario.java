package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOBinario implements ProductoDAO {
    private final File archivo;

    public ProductoDAOBinario(String nombreArchivo) {
        File carpeta = new File("data");
        if (!carpeta.exists()) carpeta.mkdirs();
        this.archivo = new File(carpeta, nombreArchivo);
    }

    @Override
    public void crear(Producto producto) {
        List<Producto> productos = listar();
        productos.add(producto);
        guardarProductos(productos);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listar()) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : listar()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) encontrados.add(p);
        }
        return encontrados;
    }

    public Producto buscarPorId(String id) {
        return buscarPorCodigo(Integer.parseInt(id));
    }

    public List<Producto> listar() {
        if (!archivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Producto>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void actualizar(Producto producto) {
        List<Producto> productos = listar();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
        guardarProductos(productos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = listar();
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }

    public void eliminar(String id) {
        eliminar(Integer.parseInt(id));
    }

    @Override
    public List<Producto> listarTodos() {
        return listar();
    }

    private void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}