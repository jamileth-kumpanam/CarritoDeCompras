package ec.edu.ups.modelo;

import java.io.*;
import java.util.List;

public class UsuarioIO {
    public static void guardarUsuariosEnBinario(List<Usuario> usuarios, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> leerUsuariosDeBinario(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        }
    }
}