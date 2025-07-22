package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarritoDAOArchivoTexto implements CarritoDAO {

    private final File archivo;

    public CarritoDAOArchivoTexto(String ruta) {
        this.archivo = new File(ruta + File.separator + "carritos.txt");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo", e);
        }
    }

    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(carrito.getCodigo()).append(",");
            sb.append(carrito.getFechaFormateada()).append(",");
            sb.append(carrito.getUsuario().getUsername()).append(",");
            sb.append(carrito.calcularTotal()).append(",");
            for (ItemCarrito item : carrito.getItems()) {
                sb.append(item.getProducto().getCodigo()).append(":")
                        .append(item.getProducto().getNombre()).append(":")
                        .append(item.getProducto().getPrecio()).append(":")
                        .append(item.getCantidad()).append(";");
            }
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el carrito", e);
        }
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
        List<Carrito> carritos = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", 5);
                if (partes.length < 5) continue;

                int codigo = Integer.parseInt(partes[0]);
                GregorianCalendar fecha = new GregorianCalendar();
                fecha.setTime(formato.parse(partes[1]));
                String username = partes[2];
                // double total = Double.parseDouble(partes[3]);
                String[] itemsTexto = partes[4].split(";");

                List<ItemCarrito> items = new ArrayList<>();
                for (String itemTexto : itemsTexto) {
                    if (!itemTexto.isEmpty()) {
                        String[] datosItem = itemTexto.split(":");
                        int codProd = Integer.parseInt(datosItem[0]);
                        String nombre = datosItem[1];
                        double precio = Double.parseDouble(datosItem[2]);
                        int cantidad = Integer.parseInt(datosItem[3]);

                        Producto producto = new Producto(codProd, nombre, precio);
                        ItemCarrito item = new ItemCarrito(producto, cantidad);
                        items.add(item);
                    }
                }

                Usuario usuario = new Usuario();
                usuario.setUsername(username);
                Carrito carrito = new Carrito(fecha, items, usuario);
                carrito.setCodigo(codigo);
                carritos.add(carrito);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer carritos", e);
        }

        return carritos;
    }

    private void guardarTodos(List<Carrito> carritos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            for (Carrito carrito : carritos) {
                StringBuilder sb = new StringBuilder();
                sb.append(carrito.getCodigo()).append(",");
                sb.append(formato.format(carrito.getFechaCreacion().getTime())).append(",");
                sb.append(carrito.getUsuario().getUsername()).append(",");
                sb.append(carrito.calcularTotal()).append(",");
                for (ItemCarrito item : carrito.getItems()) {
                    sb.append(item.getProducto().getCodigo()).append(":")
                            .append(item.getProducto().getNombre()).append(":")
                            .append(item.getProducto().getPrecio()).append(":")
                            .append(item.getCantidad()).append(";");
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar carritos", e);
        }
    }
}