package edu.escom.servidor.sockets;

import edu.escom.data.Producto;
import edu.escom.gui.ServidorConsola;
import edu.escom.servidor.backend.Catalogo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class ServerConexionII extends SwingWorker<Void, Void> {

    private ServerSocket serverSocket;
    private Socket socketCliente;
    private ServidorConsola consola;

    private ObjectOutputStream dos;
    private ObjectInputStream dis;

    private final Catalogo catalogo; //clase catalogo

    private String div;
    private JLabel estadoServidor;

    public ServerConexionII(Catalogo catalogo, JLabel estadoServidor) {
        this.catalogo = catalogo;
        this.estadoServidor = estadoServidor;
    }

    @Override
    protected Void doInBackground() throws Exception {

        int clientNo = 0;

        serverSocket = new ServerSocket(3050);
        consola = new ServidorConsola();
        consola.setVisible(true);

        while (true) {
            consola.printC("<div>Servidor en escucha - Puerto <b>3050</b> </div>");
            socketCliente = serverSocket.accept();
            if (clientNo++ == 0) {
                div = "<div>";
            } else {
                clientNo = 0;
                div = "<div>";
            }

            consola.printC(div + "Cliente conectado [<b>" + socketCliente.getInetAddress() + ":" + socketCliente.getPort() + "</b>]</div>");
            estadoServidor.setText("Cliente conectado desde " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());
            //Inicializar flujos
            inicializarCanales();
            //Enviar imagenes
            enviarImages();
            //Recibir Información hasta que sea un NULL y termine la conexión
            while (true) {
                Object recived = dis.readObject();

                if (null == recived) {
                    break;
                } else if (recived instanceof List) {
                    List<Producto> carrito = (List<Producto>) recived;
                    consola.printC(div + "Compra realizada [<b>" + carrito.size() + " producto(s) </b>]</div>");
                    estadoServidor.setText("Compra realizada [" + carrito.size() + " producto(s)]");
                    catalogo.merge(carrito);
                    
                }
            }

            consola.printC(div + "<center><b>Conexión terminada con el cliente ...</b></center></div>");
            estadoServidor.setText("Conexión terminada con el cliente ...");

        }
    }

    private void inicializarCanales() {
        try {
            dos = new ObjectOutputStream(socketCliente.getOutputStream());
            dis = new ObjectInputStream(socketCliente.getInputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error creando sockets. " + ex, "Error creando los sockets", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enviarImages() throws IOException {
        //Número de archivos
        dos.writeInt(catalogo.getProductos().size());
        dos.flush();
        //Enviar los archivos
        consola.printC(div + "Enviando imágenes del catálogo [<b>" + catalogo.getProductos().size() + " archivos</b>]</div>");
        //System.out.println("iniciando for");
        for (Producto file : catalogo.getProductos()) {
            //System.out.println("Antes de inicializar metodo enviar");
            enviar(file.getImagen());
            //System.out.println("despues de iniciar metodo enviar");
        }
       // System.out.println("afuera del for");
        //Limpiar canal
        dos.flush();
        consola.printC(div + "Imágenes enviadas correctamente</div>");

        //Enviar catalogo
        consola.printC(div + "Enviando lista de productos [<b>" + catalogo.getProductos().size() + " productos</b>]</div>");
        dos.writeObject(catalogo.getProductos());
        dos.flush();
        consola.printC(div + "Productos enviados correctamente</div>");
    }

    private void enviar(File file) throws IOException {

        //Variables locales
        byte[] buf = new byte[1024]; // Buffer de escritura
        //FileInputStream fis; // Stream de salida
        int n = 0; // Cuenta los bits que se han mandado
        //System.out.println("Metodo enviar");
        dos.writeUTF(file.getName());
        dos.writeLong(file.length());
       // System.out.println("creacion de flujos");
        try{
                    FileInputStream fis = new FileInputStream(file);
                     while ((n = fis.read(buf)) != -1) {
                    //System.out.println("adentro del while"+n);
                dos.write(buf, 0, n);
                    dos.flush();
        }
        }catch(Exception e){
            System.out.println("Error");
        }
       // System.out.println("creacion de fis");
        //Escribir al servidor
      //  System.out.println("iniciando while");
       
    }
}
