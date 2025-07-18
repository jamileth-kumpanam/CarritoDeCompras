package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de CarritoDAO que almacena los carritos en un archivo de texto.
 */
public class CarritoDAOArchivo implements CarritoDAO {
    /** Ruta del archivo donde se almacenan los carritos. */
    private final String archivo;
    /**
     * Constructor que recibe la ruta del archivo.
     * @param archivo Ruta del archivo de almacenamiento.
     */
    public CarritoDAOArchivo(String archivo) {
        this.archivo = archivo;
    }
    /**
     * Crea un nuevo carrito y lo guarda en el archivo.
     * @param carrito Carrito a crear.
     */
    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(carrito.getCodigo() + ";" + carrito.getFechaCreacion().getTimeInMillis());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Carrito buscarPorId(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Carrito carrito = Carrito.desdeString(linea);
                if (String.valueOf(carrito.getCodigo()).equals(id)) {
                    return carrito;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Busca un carrito por su código en el archivo.
     * @param codigo Código del carrito.
     * @return Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Carrito carrito = Carrito.desdeString(linea);
                if (carrito.getCodigo() == codigo) {
                    return carrito;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Carrito> listar() {
        List<Carrito> carritos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                carritos.add(Carrito.desdeString(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carritos;
    }
    /**
     * Actualiza un carrito existente en el archivo.
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                writer.write(c.getCodigo() + ";" + c.getFechaCreacion().getTimeInMillis());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Elimina un carrito del archivo por su código.
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listar();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                writer.write(c.getCodigo() + ";" + c.getFechaCreacion().getTimeInMillis());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Guarda un carrito en el archivo (crear o actualizar).
     * @param carrito Carrito a guardar.
     */
    @Override
    public void guardar(Carrito carrito) {
        if (buscarPorCodigo(carrito.getCodigo()) == null) {
            crear(carrito);
        } else {
            actualizar(carrito);
        }
    }
    /**
     * Devuelve una lista vacía (implementación pendiente).
     * @return Lista vacía.
     */
    @Override
    /**
     * Lista todos los carritos almacenados en el archivo.
     * @return Lista de carritos.
     */
    public List<Carrito> listarTodos() {
        return List.of();
    }
    /**
     * Elimina un carrito del archivo por su id (como String).
     * @param id Id del carrito a eliminar.
     */
    public void eliminar(String id) {
        List<Carrito> carritos = listar();
        carritos.removeIf(c -> String.valueOf(c.getCodigo()).equals(id));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                writer.write(c.getCodigo() + ";" + c.getFechaCreacion().getTimeInMillis());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}