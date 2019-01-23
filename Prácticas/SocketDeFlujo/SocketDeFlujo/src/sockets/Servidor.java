package sockets.servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import sockets.conexion.Conexion;

public class Servidor extends Conexion{
    public Servidor() throws IOException{super("servidor");} 
    public void startServer(){
        try{
            /* Esperando conexión*/
            System.out.println("Esperando...");
            /* Espera la conexion con el Cliente */
            cs = ss.accept(); 
            System.out.println("Cliente en línea");
            /* Se obtiene el flujo de salida del cliente para enviarle mensajes */
            salidaCliente = new DataOutputStream(cs.getOutputStream());
            /* Se le envía un mensaje al cliente usando su flujo de salida */            
            salidaCliente.writeUTF("Petición recibida y aceptada");            
            /*Se obtiene el flujo entrante desde el cliente*/            
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));            
            /* Mensajes desde el Cliente */
            while((mensajeServidor = entrada.readLine()) != null){
                System.out.println(mensajeServidor);
            }
            
            System.out.println("Fin de la conexión");
            /* Termina el socket */
            ss.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
