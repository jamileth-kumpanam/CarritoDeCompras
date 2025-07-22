package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ProductoDAO que almacena los productos en un archivo binario.
 * El archivo se guarda en la ruta especificada por el usuario.
 */
public class ProductoDAOBinario implements ProductoDAO {
    private final File archivo;

    /**
     * Precarga productos iniciales si el archivo no existe o está vacío.
     */
    private void precargarProductos() {
        if (archivo.exists() && archivo.length() > 0) return; // Ya hay datos

        List<Producto> productosIniciales = List.of(
                new Producto(1, "Producto A", 10.0),
                new Producto(2, "Producto B", 20.0),
                new Producto(3, "Producto C", 30.0)
        );
        guardarProductos(productosIniciales);
    }

    /**
     * Crea un nuevo producto y lo guarda en el archivo binario.
     */
    @Override
    public void crear(Producto producto) {
        List<Producto> productos = listar();
        productos.add(producto);
        guardarProductos(productos);
    }

    /**
     * Busca un producto por su código.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listar()) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    /**
     * Busca productos por su nombre (ignorando mayúsculas/minúsculas).
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : listar()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    /**
     * Busca un producto por su ID en formato String.
     */
    public Producto buscarPorId(String id) {
        try {
            return buscarPorCodigo(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public ProductoDAOBinario(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        File carpeta = this.archivo.getParentFile();
        if (!carpeta.exists()) carpeta.mkdirs();
    }

    /**
     * Lista todos los productos almacenados en el archivo binario.
     */
    public List<Producto> listar() {
        if (!archivo.exists() || archivo.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Actualiza un producto existente.
     */
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

    /**
     * Elimina un producto por su código.
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = listar();
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarProductos(productos);
    }

    /**
     * Elimina un producto por su ID en formato String.
     */
    public void eliminar(String id) {
        try {
            eliminar(Integer.parseInt(id));
        } catch (NumberFormatException ignored) {
        }
    }

    /**
     * Devuelve la lista de todos los productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return listar();
    }

    /**
     * Guarda la lista de productos en el archivo binario.
     */
    private void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar productos en binario", e);
        }
    }
}