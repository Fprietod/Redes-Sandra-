import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion{
    private         int          puerto =5005;        //Puerto para la conexion
    private final   String       host   ="localhost"; //Host para la conexión
    protected       String       mensajeServidor;     //Mensajes entrantes\(recibidos\) en el servidor
    protected       ServerSocket socketS;             //Socket del servidor
    protected       Socket       socketC;             // Socket del cliente 
    /*-----Flujo de datos de salida-----*/
    protected DataOutputStream salidaServidor;
    protected DataOutputStream salidaCliente;

    public Conexion(String tipo) throws IOException{
        if(tipo.equalsIgnoreCase("servidor") ){
            socketS = new ServerSocket(puerto); //Se crea el socket para el Servidor en puerto establecido
            socketC = new Socket();             //Socket para el Cliente
        }
        else
            socketC = new Socket(host, puerto); //Socket del cliente en localhost con el puerto establecido
    }
}
