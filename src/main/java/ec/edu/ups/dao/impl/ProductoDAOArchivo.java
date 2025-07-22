package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ProductoDAO que almacena los productos en un archivo de texto.
 * La ruta base y archivo se pasan en el constructor.
 */
public class ProductoDAOArchivo implements ProductoDAO {
    private final File archivo;

    /**
     * Constructor que recibe la ruta base y usa "productos.txt" dentro de esa ruta.
     * @param rutaBase Ruta donde se almacenará el archivo.
     */
    public ProductoDAOArchivo(String rutaBase) {
        File carpeta = new File(rutaBase);
        if (!carpeta.exists()) {
            if (!carpeta.mkdirs()) {
                throw new RuntimeException("No se pudo crear la carpeta: " + rutaBase);
            }
        }
        this.archivo = new File(carpeta, "productos.txt");
        precargarProductos();
    }

    /**
     * Precarga productos básicos en el archivo si no existe o está vacío.
     */
    private void precargarProductos() {
        if (archivo.exists() && archivo.length() > 0) {
            return; // Ya hay datos, no hacer nada
        }
        List<Producto> productosIniciales = List.of(
                new Producto(1, "Producto A", 10.0),
                new Producto(2, "Producto B", 20.0),
                new Producto(3, "Producto C", 30.0)
        );
        guardarProductos(productosIniciales);
    }

    @Override
    public void crear(Producto producto) {
        List<Producto> productos = listar();
        productos.add(producto);
        guardarProductos(productos);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        return listar().stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

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

    public Producto buscarPorId(String id) {
        try {
            return buscarPorCodigo(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        if (!archivo.exists()) {
            return productos; // archivo no existe aún
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Producto p = Producto.desdeString(linea);
                if (p != null) {
                    productos.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
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
        try {
            eliminar(Integer.parseInt(id));
        } catch (NumberFormatException ignored) {
        }
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
