import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Servidor extends Conexion{
    public Servidor() throws IOException{super("servidor");} 
    public void startServer(){
        try{
            System.out.println("Esperando..."); /* Esperando conexión*/
            socketC = socketS.accept();         /* Espera la conexion con el Cliente */
            System.out.println("Cliente en linea");
            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(socketC.getOutputStream());
            //Se le envía un mensaje al cliente usando su flujo de salida           
            salidaCliente.writeUTF("Peticion recibida y aceptada\n");            
            //Se obtiene el flujo entrante desde el cliente          
            BufferedReader in = new BufferedReader(new InputStreamReader(socketC.getInputStream())); 
            //---------Mensajes desde el Cliente-----------
            while((mensajeServidor = in.readLine()) != null){
                System.out.println(mensajeServidor);
            }
            
            System.out.println("Fin de la conexion");
            /* Termina el socket */
            socketS.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
