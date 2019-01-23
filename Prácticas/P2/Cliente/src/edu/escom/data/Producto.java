package edu.escom.data;

import java.io.File;
import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private String colores;
    private String tiempo;
    private String descripcion;
    private File imagen;
    private int disponibles;
    private double precio;
    private transient int cantidad;

    public Producto() {
    }

    public Producto(String nombre, String colores, String tiempo, String descripcion, File imagen, int disponibles, double precio) {
        this.nombre = nombre;
        this.colores = colores;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.disponibles = disponibles;
        this.precio = precio;
    }

    public Object getClient(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return nombre;
            case 1:
                return precio;
            case 2:
                return cantidad;
            case 3:
                return disponibles;
            case 4:
                return "Borrar";
            default:
                return null;
        }
    }

    public Object getServer(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return nombre;
            case 1:
                return disponibles;
            case 2:
                return precio;
            case 3:
                return "Editar";
            case 4:
                return "Borrar";
            default:
                return null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public File getImagen() {
        return imagen;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    
}
