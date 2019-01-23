package edu.escom.servidor.backend;

import edu.escom.data.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Catalogo {

    private List<Producto> productos;
    private File catalogoFile;

    public boolean inicializar() {

        catalogoFile = new File("catalogo.dat");
        if (catalogoFile.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(catalogoFile));
                productos = (List<Producto>) ois.readObject(); //deserializa 
                ois.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la DB.", "Error cargando la base de datos", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error cargando la DB. " + ex, "Error cargando la base de datos", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            try {
                catalogoFile.createNewFile();
                ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(catalogoFile));
                productos = new ArrayList();
                fout.writeObject(productos);
                fout.flush();
                fout.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error creando la DB." + ex, "Error creando la base de datos", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean addProducto(Producto producto) {
        productos.add(producto);
        return updateFile();
    }

    public void deleteProduct(int index) {
        productos.remove(index);
        updateFile();
    }

    public boolean updateProducto(int index, Producto producto) {
        productos.set(index, producto);
        return updateFile();
    }

    private boolean updateFile() {
        ObjectOutputStream fout = null;
        try {
            FileOutputStream writer = new FileOutputStream(catalogoFile);
            fout = new ObjectOutputStream(new FileOutputStream(catalogoFile));
            fout.writeObject(productos);
            fout.flush();
            fout.close();
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error guardando el producto", "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void merge(List<Producto> carrito) {
        for (Producto prod : carrito) {
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getNombre().equalsIgnoreCase(prod.getNombre())) {
                    updateProducto(i, prod);
                }
            }
        }

    }

}
