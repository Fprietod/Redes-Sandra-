import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion{
    public Cliente() throws IOException{
        super("cliente");
    } 
    /* Metodo de inizializacion del cliente */
    public void startClient(){
        try{   
            salidaServidor = new DataOutputStream(socketC.getOutputStream()); //Flujo de datos hacia el servidor
            int x=0;
            System.out.println("Presiona X para interrumpir el envio");
            do{ 
                System.out.print("Mensaje: ");
                Scanner leer=new Scanner(System.in);
                String msg=leer.nextLine();
                salidaServidor.writeUTF(""+msg+"\n");  //Se escribe en el servidor usando su flujo de datos
                if(msg.equals("x")||msg.equals("X"))
                    break;
            }    
            while(x!=1);  
            socketC.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }  
    }

}