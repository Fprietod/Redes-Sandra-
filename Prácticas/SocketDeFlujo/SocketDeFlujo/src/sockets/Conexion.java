package sockets.conexion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion{
    /*Puerto para la conexion*/
    private int PUERTO = 1400;
    /*Host para la conexión */
    private final String HOST = "localhost";
    /*Mensajes entrantes (recibidos) en el servidor */
    protected String mensajeServidor; 
    /*Socket del servidor*/
    protected ServerSocket ss;
    /* Socket del cliente */
    protected Socket cs; 
    /*--------------------------------*/
    /*Flujo de datos de salida */
    protected DataOutputStream salidaServidor, salidaCliente;    
    public Conexion(String tipo) throws IOException{
        if(tipo.equalsIgnoreCase("servidor")){
            /*Se crea el socket para el servidor en puerto establecido */
            ss = new ServerSocket(PUERTO);
            /*Socket para el cliente */
            cs = new Socket(); 
        }
        else
            /*Socket para el cliente en localhost en puerto establecido */
            cs = new Socket(HOST, PUERTO); 
    }
}
