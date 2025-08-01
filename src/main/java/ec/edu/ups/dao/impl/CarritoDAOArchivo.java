package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoDAOArchivo implements CarritoDAO {

    private final File archivo;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public CarritoDAOArchivo(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        try {
            if (!archivo.exists() || archivo.length() == 0) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                precargarCarritos();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar archivo de carritos", e);
        }
    }

    private void precargarCarritos() {
        List<Carrito> carritos = new ArrayList<>();

        // Crear usuario básico solo con username (sin contraseña ni rol)
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("admin");

        // Crear productos
        Producto producto1 = new Producto(1, "Producto 1", 10.0);
        Producto producto2 = new Producto(2, "Producto 2", 20.0);

        // Crear items
        List<ItemCarrito> items1 = new ArrayList<>();
        items1.add(new ItemCarrito(producto1, 2));
        items1.add(new ItemCarrito(producto2, 1));

        // Crear carrito con fecha actual
        Carrito carrito1 = new Carrito(new GregorianCalendar(), items1, usuario1);
        carrito1.setCodigo(1);

        carritos.add(carrito1);

        guardarTodos(carritos);
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
        if (buscarPorCodigo(carrito.getCodigo()) == null) {
            crear(carrito);
        } else {
            actualizar(carrito);
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        if (archivo.length() == 0) return carritos;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Formato esperado:
                // codigo,fecha,username,total,prodCod:prodNombre:prodPrecio:cantidad;prodCod:...
                String[] partes = linea.split(",", 5);
                if (partes.length < 5) continue;

                int codigo = Integer.parseInt(partes[0]);
                GregorianCalendar fecha = new GregorianCalendar();
                fecha.setTime(formatoFecha.parse(partes[1]));
                String username = partes[2];
                // No usamos total almacenado, lo calculamos en Carrito
                String itemsTexto = partes[4];

                List<ItemCarrito> items = new ArrayList<>();
                if (!itemsTexto.isEmpty()) {
                    String[] itemsArr = itemsTexto.split(";");
                    for (String itemStr : itemsArr) {
                        if (itemStr.isEmpty()) continue;
                        String[] datosItem = itemStr.split(":");
                        int codProd = Integer.parseInt(datosItem[0]);
                        String nombreProd = datosItem[1];
                        double precioProd = Double.parseDouble(datosItem[2]);
                        int cantidad = Integer.parseInt(datosItem[3]);

                        Producto producto = new Producto(codProd, nombreProd, precioProd);
                        items.add(new ItemCarrito(producto, cantidad));
                    }
                }

                Usuario usuario = new Usuario();
                usuario.setUsername(username);

                Carrito carrito = new Carrito(fecha, items, usuario);
                carrito.setCodigo(codigo);

                carritos.add(carrito);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo carritos", e);
        }
        return carritos;
    }

    private void guardarTodos(List<Carrito> carritos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                StringBuilder sb = new StringBuilder();
                sb.append(c.getCodigo()).append(",");
                sb.append(formatoFecha.format(c.getFechaCreacion().getTime())).append(",");
                sb.append(c.getUsuario().getUsername()).append(",");
                sb.append(c.calcularTotal()).append(",");

                for (ItemCarrito item : c.getItems()) {
                    sb.append(item.getProducto().getCodigo()).append(":")
                            .append(item.getProducto().getNombre()).append(":")
                            .append(item.getProducto().getPrecio()).append(":")
                            .append(item.getCantidad()).append(";");
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error guardando carritos", e);
        }
    }
}
