package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOArchivo implements CarritoDAO {
    private final String archivo;

    public CarritoDAOArchivo(String archivo) {
        this.archivo = archivo;
    }

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

    @Override
    public void eliminar(int codigo) {

    }

    @Override
    public void guardar(Carrito carrito) {

    }

    @Override
    public List<Carrito> listarTodos() {
        return List.of();
    }

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