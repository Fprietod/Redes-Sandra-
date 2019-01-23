package edu.escom.cliente.sockets;

import edu.escom.data.Producto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class ClienteConexion {
    private Socket socket;  //socket
    private ObjectOutputStream dos; //salida socket
    private ObjectInputStream dis;  //entrada socket
    private FileOutputStream fos;   //salida de los archivos
    private String ip;
    private File path;

    public ClienteConexion(String ip, File path) {
        this.path = path;
        this.ip = ip;
    }

    public List<Producto> conectar() throws IOException, ClassNotFoundException {

        byte[] buf = new byte[1024];// Buffer para transferencia de datos
        int n = 0;                  // Bytes transferidos
        long lenght;                // Tamaño que se ira reduciendo dependiendo de los bytes que se hayan transferido
        File file;                  // Archivo donde se guardara lo recibido
        
        
        socket = new Socket("192.168.1.73",3050); //socket con la ip y el puerto 3050
        dos = new ObjectOutputStream(socket.getOutputStream());
        dis = new ObjectInputStream(socket.getInputStream());
        int archivosNo = dis.readInt(); //Número de archivos 
        
        // ciclo para cada archivo
        for (int i = 0; i < archivosNo; i++) {
            //Cabecera del archivo
            file = new File(dis.readUTF()); //recibe nombre del archivo
            lenght = dis.readLong();        //tamanio del archivo
            //Ruta donde se guardaran los archivos recibidos
            fos = new FileOutputStream(path.getAbsolutePath()+'/'  + file.getName());//ruta/nombrearchivo
            //Leer img
            while (lenght > 0 && (n = dis.read(buf, 0, (int) Math.min(buf.length, lenght))) != -1) {
                fos.write(buf, 0, n);
                lenght -= n;
            }
            fos.close();
        }
        return (List<Producto>) dis.readObject();
    }

    public void enviarCambios(List<Producto> carrito) throws IOException {
        dos.writeObject(carrito);
        dos.flush();
    }

    public void cerrar() throws IOException {
     
        dos.writeObject(null);
        dos.flush();
        dos.close();
        socket.close();
        
    }
}
