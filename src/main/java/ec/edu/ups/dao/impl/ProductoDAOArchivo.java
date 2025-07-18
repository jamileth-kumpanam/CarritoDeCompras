package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ProductoDAO que almacena los productos en un archivo de texto.
 * Los datos se guardan en la carpeta 'data' junto al .jar.
 */

public class ProductoDAOArchivo implements ProductoDAO {
    private final File archivo;

    /**
     * Crea una instancia que usará el archivo especificado para almacenar productos.
     * @param nombreArchivo Nombre del archivo de texto.
     */

    public ProductoDAOArchivo(String nombreArchivo) {
        /**
         * Guarda un producto en el archivo.
         * @param producto Producto a guardar.
         */
        File carpeta = new File("data");
        if (!carpeta.exists()) carpeta.mkdirs();
        this.archivo = new File(carpeta, nombreArchivo);
    }

    @Override
    public void crear(Producto producto) {
        /**
         * Busca un producto por su código.
         * @param codigo Código del producto.
         * @return Producto encontrado o null.
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(producto.getCodigo() + ";" + producto.getNombre() + ";" + producto.getPrecio());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        /**
         * Busca productos por nombre.
         * @param nombre Nombre a buscar.
         * @return Lista de productos encontrados.
         */
        for (Producto p : listar()) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        /**
         * Actualiza un producto existente.
         * @param producto Producto actualizado.
         */
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
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                productos.add(Producto.desdeString(linea));
            }
        } catch (IOException e) {
            // Si el archivo no existe, retorna lista vacía
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        /**
         * Elimina un producto por su código.
         * @param codigo Código del producto a eliminar.
         */

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
        /**
         * Lista todos los productos almacenados.
         * @return Lista de productos.
         */
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                writer.write(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}